/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.daoimpl;

import java.util.List;
import javax.annotation.Resource;
import mum.cs490.tbs.dao.DAO;
import org.hibernate.SessionFactory;

/**
 *
 * @author Ajay
 */
public class DAOImpl<T> implements DAO<T>{

    T tt;
    @Resource(name="sessionFactory")
    SessionFactory sessionFactory;
    
    @Override
    public void create(T t) {
        sessionFactory.getCurrentSession().persist(t);
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
