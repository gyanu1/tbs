/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.dao.impl;

import java.util.List;
import mum.cs490.tbs.model.CallingRate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author PuKhanal
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY, rollbackFor = Throwable.class)
public class ReportDao implements IReportDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<CallingRate> getRateList(String country, String service) {
        Query query=sessionFactory.getCurrentSession().createQuery("select r1 FROM CallingRate r1, CallingRate r2 where r1.id.destinationCountry.code=r2.id.destinationCountry.code and r1.id.service.serviceName=r2.id.service.serviceName and r1.id.updateDate>=r2.id.updateDate and r1.id.sheetName=:value and r2.id.sheetName=:value order by r1.id.destinationCountry.country");
        query.setString("value", service+"_"+country);
        return query.list();
    }
    
}
