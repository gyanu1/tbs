/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import java.util.Calendar;
import java.util.Date;
import mum.cs490.tbs.model.CallDetail;
import mum.cs490.tbs.model.CallingCodes;
import mum.cs490.tbs.model.Customer;
import mum.cs490.tbs.model.PeakId;
import mum.cs490.tbs.model.PeakInfo;
import mum.cs490.tbs.model.Service;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author PuKhanal
 */
public class CustomerInfoReader extends ExcelReader<Customer> {

    @Override
    public Customer getRow(Row row, String sheetName) {
        Customer customer=new Customer();
        customer.setTelephoneNumber(new Double(row.getCell(1).getNumericCellValue()).longValue());
        customer.setService(new Service(row.getCell(2).getStringCellValue()));
        System.out.println(row.getCell(2).getStringCellValue());
        return customer;
    }

    
}
