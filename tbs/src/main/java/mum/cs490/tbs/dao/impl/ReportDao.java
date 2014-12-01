/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import mum.cs490.tbs.model.CallingRate;
import mum.cs490.tbs.report.BillRecord;
import org.hibernate.Criteria;
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
public class ReportDao implements IReportDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CallingRate> getRateList(String country, String service) {
        Query query = sessionFactory.getCurrentSession().createQuery("select r1 FROM CallingRate r1, CallingRate r2 where r1.id.destinationCountry.code=r2.id.destinationCountry.code and r1.id.service.serviceName=r2.id.service.serviceName and r1.id.updateDate>=r2.id.updateDate and r1.id.sourceCountry=:country and r2.id.sourceCountry=:country and r1.id.service.serviceName=:service and r2.id.service.serviceName=:service order by r1.id.destinationCountry.country");
        query.setString("country", country);
        query.setString("service", service);
        return query.list();
    }

    @Override
    public List<Map<String, Object>> genCustomerBill(Long telephoneNo) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("EXEC generateBill :telephoneNo").setResultTransformer( Criteria.ALIAS_TO_ENTITY_MAP);
        query.setLong("telephoneNo", telephoneNo);
        List<Map<String,Object>> result = query.list();
        return result;
    }

    @Override
    public List<Map<String, Object>> genMonthlyTrafficSummary(String date) {
       return sessionFactory.getCurrentSession().createSQLQuery("EXEC GetTrafficSummary :reportDate").setParameter("reportDate", date).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
    }
    

}
