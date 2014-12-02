/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import mum.cs490.tbs.model.CallingRate;
import mum.cs490.tbs.model.PeakInfo;

/**
 *
 * @author PuKhanal
 */
public interface IReportDao {

    public List<CallingRate> getRateList(String country, String service);

    public List<Map<String, Object>> genCustomerBill(Long telephoneNo);

    public List<Map<String, Object>> genMonthlyTrafficSummary(Date date);

    public PeakInfo getPeakInfo(String country, String service);

}
