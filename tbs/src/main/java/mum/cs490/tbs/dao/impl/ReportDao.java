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
import mum.cs490.tbs.model.PeakInfo;
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
        Query query = sessionFactory.getCurrentSession().createQuery("select rr from CallingRate rr where rr.id.updateDate=(select MAX(r.id.updateDate) from CallingRate r where r.id.service.serviceName=:service and r.id.sourceCountry=:country ) and rr.id.service.serviceName=:service and rr.id.sourceCountry=:country  order by rr.id.destinationCountry.country");
        query.setString("country", country);
        query.setString("service", service);
        return query.list();
    }

    @Override
    public List<Map<String, Object>> genCustomerBill(Long telephoneNo,Date date) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("EXEC generateBill :telephoneNo,:reportDate").setResultTransformer( Criteria.ALIAS_TO_ENTITY_MAP);
        query.setLong("telephoneNo", telephoneNo);
        query.setDate("reportDate", date);
        List<Map<String,Object>> result = query.list();
        return result;
    }

    @Override
    public List<Map<String, Object>> genMonthlyTrafficSummary(Date date) {
       return sessionFactory.getCurrentSession().createSQLQuery("EXEC GetTrafficSummaryByService :reportDate").setParameter("reportDate", date).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
    }
    
    @Override
    public PeakInfo getPeakInfo(String country, String service) {
        Query query=sessionFactory.getCurrentSession().createQuery("select p from PeakInfo p where p.peakId.fromCountry=:country and p.peakId.service.serviceName=:service").setString("service", service).setString("country",country);
        return (PeakInfo) query.uniqueResult();
    }

    @Override
    public List<Map<String, Object>> getSalesReport(Date date) {
        return sessionFactory.getCurrentSession().createSQLQuery("EXEC salesCommission :reportDate").setParameter("reportDate", date).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
    }

}
