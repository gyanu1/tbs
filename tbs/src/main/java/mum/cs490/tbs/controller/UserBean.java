/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import mum.cs490.tbs.dao.CallingCodeDao;
import mum.cs490.tbs.model.CallingCodes;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author gyanu
 */
@Named
@SessionScoped
public class UserBean implements Serializable {

    private String name;
    private String mobile;
   
    @Autowired
    private CallingCodeDao callingCodeDao;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public UserBean() {
        this.name = "Capstone";
        this.mobile = "9814368765";
        
    }

    public void checkData(){
        CallingCodes callCode=new CallingCodes("japan",123);
        callingCodeDao.create(callCode);
    }

}
