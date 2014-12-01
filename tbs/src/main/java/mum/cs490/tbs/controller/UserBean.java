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
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import mum.cs490.tbs.dao.impl.CallDetailDao;
import mum.cs490.tbs.dao.impl.CallingCodesDao;
import mum.cs490.tbs.dao.impl.CallingRateDao;
import mum.cs490.tbs.dao.impl.CustomerDao;
import mum.cs490.tbs.dao.impl.ServiceDao;
import mum.cs490.tbs.model.CallDetail;
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
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private CallDetailDao callDetailDao;

    private Customer customer;
    private Service service;
    private List<CallingRate> rateList;
    private StreamedContent file;
    private List<CallDetail> callDetailList;
    private List trafficSummary;
    private Map<String, Integer> monthMap;
    private int year = 2013;
    private int month = 12;

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

    @Transactional
    public List<Customer> getCustomerList() {
        log.info("inside method getCustomerList");
        return customerDao.getAll();
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

    public List<CallDetail> getCallDetailList() {
        return callDetailList;
    }

    public void setCallDetailList(List<CallDetail> callDetailList) {
        this.callDetailList = callDetailList;
    }

    public void downloadRateFile() {
        log.info("inside method downloadRateFile");
        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/img/telecom.jpg");
        file = new DefaultStreamedContent(stream, "image/jpg", "telecom.jpg");
    }

    public void goToUserHomePage() throws IOException {
        log.info("inside method goToUserHomePage ");
        Collection roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String redirect = "login.xhtml";
        for (Object role : roles) {
            log.info(role.toString());
            if (role.toString().equals("ROLE_ADMIN")) {
                redirect = "file_upload.xhtml";
            } else if (role.toString().equals("ROLE_USER")) {
                redirect = "registration.xhtml";
            }
            break;
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect(redirect);
    }

    public Map<String, Integer> getMonthMap() {
        if (monthMap == null) {
            monthMap = new LinkedHashMap<>();
            monthMap.put("Janaury", 1);
            monthMap.put("February", 2);
            monthMap.put("March", 3);
            monthMap.put("April", 4);
            monthMap.put("May", 5);
            monthMap.put("June", 6);
            monthMap.put("July", 7);
            monthMap.put("August", 8);
            monthMap.put("September", 9);
            monthMap.put("October", 10);
            monthMap.put("November", 11);
            monthMap.put("December", 12);
        }
        return monthMap;
    }

    public void setMonthMap(Map<String, Integer> monthMap) {
        this.monthMap = monthMap;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Transactional
    public void generateMonthlyTrafficSummaryByService() {
        log.info("inside method generateMonthlyTrafficSummaryByService");
        trafficSummary = callDetailDao.generateMonthlyTrafficSummaryByService(service.getServiceName(), year + "-" + month + "-" + 11);
    }

    public List getTrafficSummary() {
        return trafficSummary;
    }

    public void setTrafficSummary(List trafficSummary) {
        this.trafficSummary = trafficSummary;
    }
    
    
}
