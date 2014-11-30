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
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author PuKhanal
 */
public class CallDetailsReader extends ExcelReader<CallDetail> {

    @Override
    public CallDetail getRow(Row row, String sheetName) {
        CallDetail callDetail = new CallDetail();
        callDetail.setFromCountry(new CallingCodes(null, new Double(row.getCell(0).getNumericCellValue()).intValue()));
        callDetail.setToCountry(new CallingCodes(null, new Double(row.getCell(1).getNumericCellValue()).intValue()));
        Customer fromCustomer = new Customer();
        fromCustomer.setTelephoneNumber(new Double(row.getCell(2).getNumericCellValue()).longValue());
        callDetail.setFromCustomer(fromCustomer);
        callDetail.setToCustomerTelephoneNo(new Double(row.getCell(3).getNumericCellValue()).longValue());
        callDetail.setDuration(row.getCell(4).getNumericCellValue());
        Date date = row.getCell(5).getDateCellValue();
        callDetail.setCallDate(row.getCell(5).getDateCellValue());
        double time = row.getCell(6).getNumericCellValue();
        int minute = new Double(time % 100).intValue();
        int hour = new Double(time / 100).intValue();
//        System.out.println(time+":"+hour+":"+minute);
        Calendar cal = Calendar.getInstance();
        cal.set(date.getYear(), date.getMonth(), date.getDate(), hour, minute);
        callDetail.setCallTime(cal.getTime());
//        System.out.println(cal.getTime());
        return callDetail;
    }

}
