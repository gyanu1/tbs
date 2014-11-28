/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mum.cs490.tbs.services;

import java.util.List;
import java.util.Map;
import mum.cs490.tbs.model.CallDetail;
import mum.cs490.tbs.model.CallingCodes;
import mum.cs490.tbs.model.CallingRate;
import mum.cs490.tbs.model.Customer;
import mum.cs490.tbs.model.PeakInfo;
import mum.cs490.tbs.model.Service;

/**
 *
 * @author puneetkhanal
 */
public interface IUpdateService {
    
    public void updateRate(Map<String, List<CallingRate>> data);
    public void storeCallingCodes(Map<String, List<CallingCodes>> data);
    public void storePeakInfo(Map<String, List<PeakInfo>> data);
    public void storeCallDetails(Map<String, List<CallDetail>> data);
    public void storeCustomers(List<Customer> customers);
    public void storeServices(List<Service> services);
    
}
