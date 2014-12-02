/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import mum.cs490.tbs.model.CallingCodes;
import mum.cs490.tbs.model.CallingRate;
import mum.cs490.tbs.model.RateId;
import mum.cs490.tbs.model.Service;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author PuKhanal
 */
public class RateReader extends ExcelReader<CallingRate> {

    private Date updateDate;

    @Override
    public CallingRate getRow(Row row, String fileName) {
        if (updateDate == null) {
            int time = Integer.parseInt(fileName.replace(".xls", "").replace("Rates_", ""));
            int day = time % 100;
            time = time / 100;
            int month = time % 100;
            int year = time / 100;
            System.out.println(year + ":" + month + ":" + day);
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            updateDate = calendar.getTime();
            System.out.println(updateDate);
        }

        CallingRate callingRate = new CallingRate();
        RateId rateId = new RateId();
        rateId.setDestinationCountry(new CallingCodes(null, new Double(row.getCell(0).getNumericCellValue()).intValue()));
        rateId.setUpdateDate(updateDate);
        String sheetName = row.getSheet().getSheetName();
        String[] split = sheetName.split("_");

        rateId.setService(new Service(split[0]));
        rateId.setSourceCountry(split[1]);
        callingRate.setId(rateId);
        callingRate.setPeak(row.getCell(1).getNumericCellValue());
        callingRate.setOffPeak(row.getCell(2).getNumericCellValue());
        return callingRate;
    }

}
