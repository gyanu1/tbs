/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import mum.cs490.tbs.dao.UserDao;
import mum.cs490.tbs.dao.impl.CallingCodesDao;
import mum.cs490.tbs.dao.impl.CallingRateDao;
import mum.cs490.tbs.dao.impl.CustomerDao;
import mum.cs490.tbs.dao.impl.ServiceDao;
import mum.cs490.tbs.model.CallingCodes;
import mum.cs490.tbs.model.CallingRate;
import mum.cs490.tbs.model.Customer;
import mum.cs490.tbs.model.Service;
import mum.cs490.tbs.model.TbsUser;
import mum.cs490.tbs.model.UserRole;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
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

    @Autowired
    private CallingCodesDao callingCodesDao;
    @Autowired
    private ServiceDao serviceDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CallingRateDao callingRateDao;

  
    
    private Customer customer;
    private Service service;
    private List<CallingRate> rateList;
    private StreamedContent file;


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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public UserBean() {
        customer = new Customer();
        customer.setService(new Service());
        service = new Service();
    }
    
    public List<Customer> getCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer(9841497163L, new Service("ntc")));
        customerList.add(new Customer(984763163L, new Service("ncell")));
        customerList.add(new Customer(976563163L, new Service("airtel")));
         customerList.add(new Customer(9841497163L, new Service("ntc")));
        customerList.add(new Customer(984763163L, new Service("ncell")));
        customerList.add(new Customer(976563163L, new Service("airtel")));
         customerList.add(new Customer(9841497163L, new Service("ntc")));
        customerList.add(new Customer(984763163L, new Service("ncell")));
        customerList.add(new Customer(976563163L, new Service("airtel")));
        return customerList;
    }

    @Transactional
    public List<Service> getServiceList() {
        log.info("inside method getServiceList");
          return serviceDao.getAll();
      }

    @Transactional
    public List<CallingCodes> getCallingCodesList() {
        log.info("inside method getCallingCodesList");
        return callingCodesDao.getAll();
    }

    @Transactional
    public List<CallingCodes> autocompleteCountry(String query) {
        log.info("inside method autocompleteCountry");
        List<CallingCodes> allCallingCodes = callingCodesDao.getAll();
        List<CallingCodes> filteredCallingCodes = new ArrayList<CallingCodes>();

        for (int i = 0; i < allCallingCodes.size(); i++) {
            CallingCodes callCode = allCallingCodes.get(i);
            if (callCode.getCountry().toLowerCase().startsWith(query)) {
                filteredCallingCodes.add(callCode);
            }
        }

        return filteredCallingCodes;
    }
    
    @Transactional
    public void saveCustomer() {
        log.info("inside method saveCustomer");
        Service service = serviceDao.findByName(customer.getService().getServiceName());
        customer.setService(service);
        customerDao.store(customer);
        customer = new Customer();
    }

    @Transactional
    public void getCallingRates() {
        rateList = callingRateDao.getAll();

    }

    @Transactional
    public void searchCallingRates() {
        log.info("inside method searchCallingRates");
        log.info("country : " + service.getCountry() + "  :: service name : " + service.getServiceName());
        if (service.getCountry().trim().equals("United States of America")) {
            service.setCountry("USA");
        }
        rateList = callingRateDao.getCallingRatesByCountryAndService(service.getServiceName(), service.getCountry());
    }

    public Service getService() {
        return service;
    }

    public void setService(Service Service) {
        this.service = Service;
    }

    public List<CallingRate> getRateList() {
        return rateList;
    }

    public void setRateList(List<CallingRate> rateList) {
        this.rateList = rateList;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public void downloadRateFile() {
        log.info("inside method downloadRateFile");
        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/img/telecom.jpg");
        file = new DefaultStreamedContent(stream, "image/jpg", "telecom.jpg");

    }

}
