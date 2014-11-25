/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.controller;

import mum.cs490.tbs.services.FileService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PuKhanal
 */
public class FileControllerTest {
    
    
    @BeforeClass
    public static void beforeClass(){
        
    }
    
    @AfterClass
    public static void afterClass(){
        
    }
    @Before
    public void beforeTest(){
    }
    
    @After
    public void afterTest(){
    }
    
    @Test
    public void testFileService(){
        FileService fileService=new FileService();
        Assert.assertTrue(fileService.save("file"));
    }
    
     @Test
    public void testFileService1(){
        FileService fileService=new FileService();
        Assert.assertTrue(fileService.save("file"));
    }
    
}
