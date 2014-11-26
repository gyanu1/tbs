/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import javax.mail.MessagingException;
import org.junit.Test;

/**
 *
 * @author PuKhanal
 */
public class MailServiceTest extends BaseTestCase{
    
    @Test
    public void mailTest() throws MessagingException{
        mailService.sendMail("puneetkhanal@gmail.com", "hi", "hi");
    }
}
