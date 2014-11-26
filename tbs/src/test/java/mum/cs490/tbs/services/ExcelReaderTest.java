/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mum.cs490.tbs.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author puneetkhanal
 */
public class ExcelReaderTest {
    
    @Test
    public void excelReaderTest() throws IOException{
        CountryCodeReader excelReader=new CountryCodeReader();
        Map<Integer,List<Country>>  excelSheets=excelReader.loadWorkBook("data/calling_code.xls");
        System.out.println(excelSheets);
        Assert.assertFalse(excelSheets.isEmpty());
    }
}
