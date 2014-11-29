/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.dao.impl;

import java.lang.reflect.ParameterizedType;
import javax.annotation.Resource;
import mum.cs490.tbs.dao.IGenericDaoII;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gyanu
 */
@Repository
public class GenericDaoII<T> implements IGenericDaoII<T> {
    @Resource(name = "sessionFactory")
    SessionFactory sessionFactory;
    private Class entityType;
//    public GenericDaoII() {
//        this.entityType = ((Class) ((ParameterizedType) getClass()
//                .getGenericSuperclass()).getActualTypeArguments()[0]);
//    }
    @Override
    public void store(T t) {
        sessionFactory.getCurrentSession().merge(t);
    }

    @Override
    public void update(T t) {
        sessionFactory.getCurrentSession().update(t);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
