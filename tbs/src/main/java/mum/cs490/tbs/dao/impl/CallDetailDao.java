/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.dao.impl;

import java.util.List;
import mum.cs490.tbs.model.CallDetail;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gyanu
 */
@Repository
public class CallDetailDao extends GenericDaoII<CallDetail> {
    public List<CallDetail> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from CallDetail").list();
    }

    public List<CallDetail> generateCustomerBill(long telephone, String country) {
        return sessionFactory.getCurrentSession().createQuery("from CallDetail c where c.fromCustomer.telephoneNumber=:telephone").setParameter("telephone", telephone).list();
    }

    public List generateMonthlyTrafficSummaryByService(String serviceName) {
        return sessionFactory.getCurrentSession().createSQLQuery("CALL GetTrafficSummaryByService(:serviceName)").setParameter("serviceName", serviceName).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
    }
}
