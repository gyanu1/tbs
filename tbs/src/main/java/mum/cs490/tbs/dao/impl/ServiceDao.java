/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.dao.impl;

import java.util.List;
import mum.cs490.tbs.model.Service;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gyanu
 */
@Repository
public class ServiceDao extends GenericDaoII<Service> {
   public List<Service> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Service").list();
    }
    
    public Service findByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Service where serviceName=:serviceName");
        query.setParameter("serviceName", name);
        return (Service) query.list().get(0);
    }

}
