/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import java.util.List;
import mum.cs490.tbs.dao.impl.IReportDao;
import mum.cs490.tbs.model.CallingRate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author PuKhanal
 */
@Service("reportService")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ReportService implements IReportService{

    @Autowired
    private IReportDao reportDao;
    
    @Override
    public List<CallingRate> getRateList(String country, String service) {
       return reportDao.getRateList(country, service);
    }
    
}
