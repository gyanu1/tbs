/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.controller;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import mum.cs490.tbs.dao.UserDao;
import mum.cs490.tbs.model.User;
import mum.cs490.tbs.model.UserRole;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gyanu
 */
@Named
@SessionScoped
public class UserBean implements Serializable {
    static Logger log = Logger.getLogger(UserBean.class.getName());

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public void createUser() {
        List<User> userList = userDao.findUserByName("admin");
        if (userList.isEmpty()) {
            UserRole role = new UserRole("ROLE_ADMIN");
            userDao.saveUserRole(role);
            User user = new User("admin", encoder.encode("admin123"));
            user.setUserRole(role);
            userDao.saveUser(user);
            role = new UserRole("ROLE_USER");
            userDao.saveUserRole(role);
            user = new User("salesrep", encoder.encode("salesrep123"));
            user.setUserRole(role);
            userDao.saveUser(user);
        }
        
    }


}
