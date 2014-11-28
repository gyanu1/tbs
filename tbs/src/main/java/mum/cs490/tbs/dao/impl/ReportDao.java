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
        Query query=sessionFactory.getCurrentSession().createQuery("select r from CallingRate r where r.id.sheetName=:value");
        query.setString("value", service+"_"+country);
        return query.list();
    }
    
}
