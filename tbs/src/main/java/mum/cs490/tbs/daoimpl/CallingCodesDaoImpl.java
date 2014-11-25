/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.daoimpl;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import mum.cs490.tbs.dao.CallingCodeDao;
import mum.cs490.tbs.model.CallingCodes;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author GMaharjan
 */
@Repository
public class CallingCodesDaoImpl implements CallingCodeDao{

    
    @Resource(name="sessionFactory")
    SessionFactory sessionFactory;
   
    @Override
    @Transactional
    public void create(CallingCodes callCode) {
         sessionFactory.getCurrentSession().persist(callCode);
    }
    
}
