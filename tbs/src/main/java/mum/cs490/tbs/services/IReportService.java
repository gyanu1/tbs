/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import mum.cs490.tbs.model.CallingRate;

/**
 *
 * @author PuKhanal
 */
public interface IReportService {
    
    public List<CallingRate> exportRateSheet(String basePath,String country, String service);
    
    public List<Map<String, Object>> generateCustomerBill(String basePath,Long telephoneNo,Date date);
    
    public List<Map<String, Object>> generateTrafficSummary(String basePath,Date date);
    
    public List<Map<String, Object>> generateSalesCommissionReport(String basePath,Date date);
    
    
}
