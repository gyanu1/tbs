/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import java.util.Date;
import java.util.List;
import mum.cs490.tbs.model.CallingRate;

/**
 *
 * @author PuKhanal
 */
public interface IReportService {
    
    public String exportRateSheet(String basePath,String country, String service);
    
    public String generateCustomerBill(String basePath,Long telephoneNo);
    
    public String generateTrafficSummary(String basePath,Date date);
    
    
}
