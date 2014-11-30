--mysql query begin 

-- DROP PROCEDURE IF EXISTS `GetCountryByCode` ;
-- 
-- DELIMITER $$
-- CREATE PROCEDURE `GetCountryByCode`(string_code INT(11))
-- BEGIN
-- select country from CallingCodes where code=string_code;
-- END$$ 

DROP PROCEDURE IF EXISTS `GetTrafficSummaryByService` ;

DELIMITER $$
CREATE PROCEDURE `GetTrafficSummaryByService`(string_serviceName VARCHAR(75))
BEGIN
SELECT cd.fromCountry_code,cd.toCountry_code,sum(cd.duration) as total_mins  FROM CallDetail cd WHERE  cd.fromCustomer_telephoneNumber IN(SELECT telephoneNumber FROM Customer c  WHERE c.Service_serviceName = string_serviceName) GROUP BY cd.fromCountry_code,cd.toCountry_code;
END$$ 

DROP PROCEDURE IF EXISTS `GetTrafficSummary`; 

DELIMITER $$
CREATE PROCEDURE `GetTrafficSummary`()
BEGIN
SELECT s.serviceName,cd.fromCountry_code,cd.toCountry_code,sum(cd.duration) as total_mins  FROM CallDetail cd INNER JOIN Customer c ON c.telephoneNumber=cd.fromCustomer_telephoneNumber INNER JOIN Service s ON s.serviceName=c.Service_serviceName GROUP BY  s.serviceName,cd.fromCountry_code,cd.toCountry_code;
END$$ 

-- mysql query end