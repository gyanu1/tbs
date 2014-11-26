/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mum.cs490.tbs.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import mum.cs490.tbs.model.CallingCodes;
import mum.cs490.tbs.model.CallingRate;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author puneetkhanal
 */
public class ExcelReaderTest {
    
    @Test
    public void callingCodeReaderTest() throws IOException{
        CallingCodeReader excelReader=new CallingCodeReader();
        Map<String,List<CallingCodes>>  excelSheets=excelReader.loadWorkBook("data/calling_code.xls");
        System.out.println(excelSheets);
        Assert.assertFalse(excelSheets.isEmpty());
    }
    
     @Test
    public void rateReaderTest() throws IOException{
        RateReader excelReader=new RateReader();
        Map<String,List<CallingRate>>  excelSheets=excelReader.loadWorkBook("data/Rates_20130901.xls");
        System.out.println(excelSheets);
        Assert.assertFalse(excelSheets.isEmpty());
    }
    
    @Test
    public void callDetailsReaderTest() throws IOException{
        RateReader excelReader=new RateReader();
        Map<String,List<CallingRate>>  excelSheets=excelReader.loadWorkBook("data/Calls_Dec2013.xls");
        System.out.println(excelSheets);
        Assert.assertFalse(excelSheets.isEmpty());
    }
}
