/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import javax.transaction.Transactional;
import mum.cs490.tbs.dao.UserDao;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 *
 * @author PuKhanal
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class BaseTestCase {

    @Autowired
    protected MailService mailService;

    @Autowired
    protected IUpdateService updateService;
    
    @Autowired
    protected IReportService reportService;
    
    @Autowired
    protected UserDao userDao;
    
    @Autowired
    protected PasswordEncoder encoder;

}
