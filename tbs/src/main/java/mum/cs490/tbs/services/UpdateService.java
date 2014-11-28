/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import java.util.List;
import java.util.Map;
import mum.cs490.tbs.dao.IGenericDao;
import mum.cs490.tbs.model.CallDetail;
import mum.cs490.tbs.model.CallingCodes;
import mum.cs490.tbs.model.CallingRate;
import mum.cs490.tbs.model.Customer;
import mum.cs490.tbs.model.PeakInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author puneetkhanal
 */
@Service("updateService")
public class UpdateService implements IUpdateService {

    @Autowired
    private IGenericDao<CallingCodes> callingCodeDao;

    @Autowired
    private IGenericDao<CallDetail> callDetailDao;

    @Autowired
    private IGenericDao<Customer> customerDao;

    @Autowired
    private IGenericDao<mum.cs490.tbs.model.Service> serviceDao;

    @Autowired
    private IGenericDao<CallingRate> rateDao;

    @Autowired
    private IGenericDao<PeakInfo> peakDao;

    @Override
    public void updateRate(Map<String, List<CallingRate>> data) {
        for (Map.Entry<String, List<CallingRate>> entry : data.entrySet()) {
            for (CallingRate callingRate : entry.getValue()) {
                rateDao.store(callingRate);
            }
        }
    }

    @Override
    public void storeCallingCodes(Map<String, List<CallingCodes>> data) {
        for (Map.Entry<String, List<CallingCodes>> entry : data.entrySet()) {
            for (CallingCodes callingCode : entry.getValue()) {
                callingCodeDao.store(callingCode);
            }
        }
    }

    @Override
    public void storeCallDetails(Map<String, List<CallDetail>> data) {
        for (Map.Entry<String, List<CallDetail>> entry : data.entrySet()) {
            for (CallDetail callDetail : entry.getValue()) {
                callDetailDao.store(callDetail);
            }
        }
    }

    @Override
    public void storeCustomers(List<Customer> customers) {
        for (Customer customer : customers) {
            customerDao.store(customer);
        }
    }

    @Override
    public void storeServices(List<mum.cs490.tbs.model.Service> services) {
        for (mum.cs490.tbs.model.Service service : services) {
            serviceDao.store(service);
        }
    }

    @Override
    public void storePeakInfo(Map<String, List<PeakInfo>> data) {
        for (Map.Entry<String, List<PeakInfo>> entry : data.entrySet()) {
            for (PeakInfo peakInfo : entry.getValue()) {
                peakDao.store(peakInfo);
            }
        }
    }

}
