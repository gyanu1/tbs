DROP PROCEDURE generateBill
DROP PROCEDURE GetTrafficSummaryByService
DROP PROCEDURE salesCommission

CREATE PROCEDURE generateBill 
@telephoneNo BIGINT,
@reportDate DATE
AS
--DECLARE @telephoneNo BIGINT
--SET @telephoneNo=5623401075
select cd.*,c.Service_serviceName as service into #T1 from CallDetail cd join Customer c on cd.fromCustomer_telephoneNumber=c.telephoneNumber join CallingCodes cc on cd.fromCountry_code=cc.code where cd.fromCustomer_telephoneNumber=@telephoneNo and MONTH(cd.callDate)=MONTH(@reportDate) and YEAR(cd.callDate)=YEAR(@reportDate)
--select * from #T1

select t1.*,cc.country into #T22 from #T1 t1 join CallingCodes cc on t1.fromCountry_code=cc.code

select t22.*,cc.country as toCountry into #T2 from #T22 t22 join CallingCodes cc on t22.toCountry_code=cc.code

--select count(*) from #T2 group by id

select case 
     when t2.callTime > pi.peakStart and t2.callTime < pi.offPeakStart THEN 1
     Else 0
     END as peakflag,t2.* into #t3
		from PeakInfo pi
		join #T2 t2 on pi.fromCountry=t2.country and t2.service=pi.service_serviceName
		
--select * from #t3  
select t3.toCountry_code as a,t3.fromCountry_code as b,t3.service as c ,max(cr.updateDate) as maxDate into #t44 from #T3 t3, CallingRate cr where t3.toCountry_code=cr.destinationCountry_code and t3.country=cr.sourceCountry and t3.service=cr.service_serviceName and cr.updateDate<t3.callDate group by t3.toCountry_code,t3.fromCountry_code,t3.service

--select * from #t44
--select count(*) from #T3 group by id

select t44.*,t3.*,r.* into #t4 from #t44 t44, #t3 t3,CallingRate r where t44.a=t3.toCountry_code and t44.b=t3.fromCountry_code and t44.c=t3.service and t44.a=r.destinationCountry_code and t3.country=r.sourceCountry and t44.c=r.service_serviceName and t44.maxDate=r.updateDate

--select * from #t4
--select count(*) from #T4 group by id

--select * from #t4

select case 
     when t4.peakflag=1 THEN (peak*duration)/60
     Else (offpeak*duration)/60
     END as amount, t4.service,t4.duration,t4.sourceCountry,t4.toCountry,t4.callTime,t4.toCustomerTelephoneNo
		from #t4 t4 order by t4.id
		
GO

SELECT c.Service_serviceName as service,cd.fromCountry_code,cd.toCountry_code,sum(cd.duration) as total_mins  
FROM CallDetail cd inner join Customer c on cd.fromCustomer_telephoneNumber=c.telephoneNumber WHERE MONTH(cd.callDate)=12 
	AND YEAR(cd.callDate)=2013 GROUP BY c.Service_serviceName,cd.fromCountry_code,cd.toCountry_code;


--DELIMITER $$
CREATE PROCEDURE GetTrafficSummaryByService
@report_date DATE

AS
SELECT c.Service_serviceName as service,cd.fromCountry_code,cd.toCountry_code,sum(cd.duration) as total_mins  into #T2
FROM CallDetail cd inner join Customer c on cd.fromCustomer_telephoneNumber=c.telephoneNumber WHERE MONTH(cd.callDate)=MONTH(@report_date) 
	AND YEAR(cd.callDate)=YEAR(@report_date) GROUP BY c.Service_serviceName,cd.fromCountry_code,cd.toCountry_code 

 


select t2.*,c.country as sourceCountry into #T3 from #T2 t2 inner join CallingCodes c on t2.fromCountry_code=c.code
select t3.*,c.country as destinationCountry from #T3 t3 inner join CallingCodes c on t3.toCountry_code=c.code order by t3.service, t3.sourceCountry

GO




CREATE PROCEDURE salesCommission 
@reportDate DATE
AS
--DECLARE @telephoneNo BIGINT
--SET @telephoneNo=5623401075
select cd.*,c.Service_serviceName as service into #T1 from CallDetail cd join Customer c on cd.fromCustomer_telephoneNumber=c.telephoneNumber join CallingCodes cc on cd.fromCountry_code=cc.code where MONTH(cd.callDate)=MONTH(@reportDate) and YEAR(cd.callDate)=YEAR(@reportDate)
--select * from #T1

select t1.*,cc.country into #T22 from #T1 t1 join CallingCodes cc on t1.fromCountry_code=cc.code

select t22.*,cc.country as toCountry into #T2 from #T22 t22 join CallingCodes cc on t22.toCountry_code=cc.code

--select count(*) from #T2 group by id

select case 
     when t2.callTime > pi.peakStart and t2.callTime < pi.offPeakStart THEN 1
     Else 0
     END as peakflag,t2.* into #t3
		from PeakInfo pi
		join #T2 t2 on pi.fromCountry=t2.country and t2.service=pi.service_serviceName
		
--select * from #t3  
select t3.toCountry_code as a,t3.fromCountry_code as b,t3.service as c ,max(cr.updateDate) as maxDate into #t44 from #T3 t3, CallingRate cr where t3.toCountry_code=cr.destinationCountry_code and t3.country=cr.sourceCountry and t3.service=cr.service_serviceName and cr.updateDate<t3.callDate group by t3.toCountry_code,t3.fromCountry_code,t3.service

--select * from #t44
--select count(*) from #T3 group by id

select t44.*,t3.*,r.* into #t4 from #t44 t44, #t3 t3,CallingRate r where t44.a=t3.toCountry_code and t44.b=t3.fromCountry_code and t44.c=t3.service and t44.a=r.destinationCountry_code and t3.country=r.sourceCountry and t44.c=r.service_serviceName and t44.maxDate=r.updateDate

--select * from #t4
--select count(*) from #T4 group by id

--select * from #t4

select case 
     when t4.peakflag=1 THEN (peak*duration)/60
     Else (offpeak*duration)/60
     END as amount, t4.service,t4.duration,t4.sourceCountry,t4.toCountry,t4.callTime,t4.toCustomerTelephoneNo,t4.fromCustomer_telephoneNumber into #t55
		from #t4 t4 order by t4.id
		
	--select distinct fromCustomer_telephoneNumber from #t55
		
	select c.firstname +' '+ c.lastname as name,c.salesRep_id, SUM(amount*c.commission/100) as commission
	from #t55 t55 join
	Customer c 
	on c.telephoneNumber = t55.fromCustomer_telephoneNumber
	group by c.firstname+' '+c.lastname,c.salesRep_id
		
GO

EXEC salesCommission "2013-12-4"
EXEC GetTrafficSummaryByService '2013-12-4'
EXEC generateBill '7139375437','2013-12-4'

select * from TbsUser
select c.updateDate from CallingRate c group by c.updateDate
select * from CallDetail
