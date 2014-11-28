/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import mum.cs490.tbs.dao.UserDao;
import mum.cs490.tbs.model.TbsUser;
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
        List<TbsUser> userList = userDao.findUserByName("admin");
        if (userList.isEmpty()) {
            UserRole role = new UserRole("ROLE_ADMIN");
            userDao.saveUserRole(role);
            TbsUser user = new TbsUser("admin", encoder.encode("admin123"));
            user.setUserRole(role);
            userDao.saveUser(user);
            role = new UserRole("ROLE_USER");
            userDao.saveUserRole(role);
            user = new TbsUser("salesrep", encoder.encode("salesrep123"));
            user.setUserRole(role);
            userDao.saveUser(user);
        }
        
    }

    // This is the action method called when the user clicks the "login" button
    public String doLogin() throws IOException, ServletException {
        log.info("inside method doLogin ");
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                .getRequestDispatcher("/j_spring_security_check");

        dispatcher.forward((ServletRequest) context.getRequest(),
                (ServletResponse) context.getResponse());

        FacesContext.getCurrentInstance().responseComplete();
        // It's OK to return null here because Faces is just going to exit.
        return null;
    }

    public String logout() {
        log.info("inside method logout");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "logout";
    }


}
