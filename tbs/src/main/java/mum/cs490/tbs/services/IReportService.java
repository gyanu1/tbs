/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import java.util.List;
import mum.cs490.tbs.model.CallingRate;

/**
 *
 * @author PuKhanal
 */
public interface IReportService {
    
    public List<CallingRate> getRateList(String country, String service);
    
    
}
