/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.daoimpl;

import javax.annotation.Resource;
import mum.cs490.tbs.dao.CustomerDao;
import mum.cs490.tbs.model.Customer;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ajay
 */
public class CustomerDaoImpl implements CustomerDao{

    @Resource(name="sessionFactory")
    SessionFactory sessionFactory;
    
    @Override
    @Transactional
    public void create(Customer customer) {
        sessionFactory.getCurrentSession().persist(customer);    
    }

    @Override
    @Transactional
    public void update(Customer customer) {
        sessionFactory.getCurrentSession().update(customer);
    }
    
    
}
