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
select t3.toCountry_code as a,t3.fromCountry_code as b,t3.service as c ,max(cr.updateDate) as maxDate into #t44 from #T3 t3, CallingRate cr where t3.toCountry_code=cr.destinationCountry_code and t3.country=cr.sourceCountry and t3.service=cr.service_serviceName and cr.updateDate<=t3.callDate group by t3.toCountry_code,t3.fromCountry_code,t3.service

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
		
	select c.salesRep_id, SUM(amount*c.commission/100) as commission
	from #t55 t55 join
	Customer c 
	on c.telephoneNumber = t55.fromCustomer_telephoneNumber
	group by c.salesRep_id
		
GO



