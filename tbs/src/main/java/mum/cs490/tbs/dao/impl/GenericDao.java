/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.dao.impl;

import java.util.List;
import javax.annotation.Resource;
import mum.cs490.tbs.dao.IGenericDao;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ajay
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY, rollbackFor = Throwable.class)
public class GenericDao<T> implements IGenericDao<T>{

    T tt;
    @Resource(name="sessionFactory")
    SessionFactory sessionFactory;
    
    @Override
    public void store(T t) {
        sessionFactory.getCurrentSession().merge(t);
    }

    @Override
    public void update(T t) {
        sessionFactory.getCurrentSession().update(t);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from "+ tt.getClass().getName()).list();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
