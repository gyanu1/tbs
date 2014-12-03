/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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
import mum.cs490.tbs.dao.impl.IReportDao;
import mum.cs490.tbs.dao.impl.ServiceDao;
import mum.cs490.tbs.model.CallDetail;
import mum.cs490.tbs.model.CallingCodes;
import mum.cs490.tbs.model.CallingRate;
import mum.cs490.tbs.model.Customer;
import mum.cs490.tbs.model.Service;
import mum.cs490.tbs.model.TbsUser;
import mum.cs490.tbs.model.UserRole;
import mum.cs490.tbs.services.IReportService;
import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    @Autowired
    private IReportDao reportDao;
    @Autowired
    private IReportService reportService;

    private Customer customer;
    private Service service;
    private List<CallingRate> rateList;
    private StreamedContent file;
    private List<CallDetail> callDetailList;
    private List trafficSummary;
    private Map<Integer, String> mapCountryByCode;
    private Date selectedDate;
    private List<Map<String, Object>> commissionReport;
    private List<Map<String, Object>> customerBill;

    @Transactional
    public void createUser() {
        List<TbsUser> userList = userDao.findUserByName("admin");
        if (userList.isEmpty()) {
            UserRole role = new UserRole("ROLE_ADMIN");
            userDao.saveUserRole(role);
            TbsUser user = new TbsUser("admin", encoder.encode("admin123"));
            user.setId(1000L);
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
        initCustomer();
        service = new Service();
    }

    public void initCustomer() {
        customer = new Customer();
        customer.setService(new Service());
        customer.setCommission(10);
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        log.info("name : " + name);
        Service service = serviceDao.findByName(customer.getService().getServiceName());
        customer.setService(service);
        customer.setSalesRep(userDao.findUserByName(auth.getName()).get(0));
        customerDao.store(customer);
        initCustomer();
    }

    @Transactional
    public void getCallingRates() {
        rateList = callingRateDao.getAll();

    }

    @Transactional
    public void searchCallingRates() {
        log.info("inside method searchCallingRates");
        log.info("country : " + service.getCountry() + "  :: service name : " + service.getServiceName());
        log.info(((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/"));
        String basePath = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/");
        // service.setCountry("USA");
        rateList = reportDao.getRateList(service.getCountry(), service.getServiceName());
        reportService.exportRateSheet(basePath, service.getCountry(), service.getServiceName());

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
        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/uploads/export/RateSheet.pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", "RateSheet.pdf");
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

    @Transactional
    public void generateMonthlyTrafficSummaryByService() {
        log.info("inside method generateMonthlyTrafficSummaryByService");
        /**
         * traffic summary by service in admin view*
         */
        log.info(getDateString(selectedDate));
        trafficSummary = callDetailDao.generateMonthlyTrafficSummaryByService(service.getServiceName(), getDateString(selectedDate));
        /**
         * traffic summary for report*
         */
        //  trafficSummary=reportDao.genMonthlyTrafficSummary( year + "-" + month + "-" + 11);
    }

    public List getTrafficSummary() {
        return trafficSummary;
    }

    public void setTrafficSummary(List trafficSummary) {
        this.trafficSummary = trafficSummary;
    }

    @Transactional
    public Map<Integer, String> getMapCountryByCode() {
        if (mapCountryByCode == null) {
            mapCountryByCode = new HashMap<>();
            for (CallingCodes code : callingCodesDao.getAll()) {
                mapCountryByCode.put(code.getCode(), code.getCountry());
            }
        }
        return mapCountryByCode;
    }

    public void setMapCountryByCode(Map<Integer, String> mapCountryByCode) {
        this.mapCountryByCode = mapCountryByCode;
    }

    public void generateCommissionReport() {

    }

    public List<Map<String, Object>> getCommissionReport() {
        if (commissionReport == null) {
            commissionReport = new ArrayList<>();
            Map map = new HashMap<String, Object>();
            map.put("name", "salesrep1");
            map.put("commission", 1234);
            commissionReport.add(map);
            map = new HashMap<String, Object>();
            map.put("name", "salesrep2");
            map.put("commission", 4234);
            commissionReport.add(map);
        }
        return commissionReport;
    }

    public void setCommissionReport(List<Map<String, Object>> commissionReport) {
        this.commissionReport = commissionReport;
    }

    @Transactional
    public List<Customer> getCustomerListBySalesRep() {
        log.info("inside method getCustomerListBySalesRep");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        TbsUser user = userDao.findUserByName(auth.getName()).get(0);
        return user.getCustomerList();
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    public void onDateSelect(SelectEvent event) {
        log.info("inside method onDateSelect");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    @Transactional
    public void generateCustomerBill() {
        log.info("inside method generateCustomerBill " + getDateString(selectedDate));
        String basePath = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/resources/");
        String path = reportService.generateCustomerBill(basePath, customer.getTelephoneNumber(), selectedDate);
        log.info("path : " + path);
        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/uploads/export/CustomerBill.pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", "CustomerBill.pdf");
        customerBill=reportDao.genCustomerBill(customer.getTelephoneNumber(), selectedDate);
        log.info(customerBill.toString());
    }

    public String getDateString(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        return year + "-" + (month + 1) + "-" + day;
    }

    public List<Map<String, Object>> getCustomerBill() {
        return customerBill;
    }

    public void setCustomerBill(List<Map<String, Object>> customerBill) {
        this.customerBill = customerBill;
    }

}
