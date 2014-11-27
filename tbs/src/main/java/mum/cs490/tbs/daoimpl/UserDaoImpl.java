/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.daoimpl;

import java.util.List;
import javax.annotation.Resource;
import mum.cs490.tbs.dao.UserDao;
import mum.cs490.tbs.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gyanu
 */
public class UserDaoImpl implements UserDao {

    @Resource(name = "sessionFactory")
    SessionFactory sessionFactory;

    @Transactional
    @Override
    public List<User> findUserByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where username=:username");
        query.setParameter("username", name);
        return  query.list();
    }

    @Override
    public User SaveUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
