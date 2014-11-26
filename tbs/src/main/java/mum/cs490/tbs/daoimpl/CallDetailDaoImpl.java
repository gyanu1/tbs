/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.daoimpl;

import java.util.List;
import javax.annotation.Resource;
import mum.cs490.tbs.dao.CallDetailDao;
import mum.cs490.tbs.model.CallDetail;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ARijal
 */
public class CallDetailDaoImpl implements CallDetailDao{
    
    @Resource(name = "sessionFactory")
    SessionFactory sessionFactory;
    
    @Override
    @Transactional
    public void create(CallDetail callDetail) {
         sessionFactory.getCurrentSession().persist(callDetail);
    }

    @Override
    public void update(CallDetail callDetail) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        sessionFactory.getCurrentSession().update(callDetail);
    }
    
    public List<CallDetail> get(Long telephoneNumber){
        return sessionFactory.getCurrentSession().createQuery("from CallDetail c where c.fromCustomer.telephoneNumber = " + telephoneNumber).list();
    }
}
