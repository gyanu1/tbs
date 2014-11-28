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
public class PeakInfoReader extends ExcelReader<PeakInfo> {

    @Override
    public PeakInfo getRow(Row row, String sheetName) {
        PeakInfo peakInfo = new PeakInfo();
        PeakId peakId = new PeakId();
        peakId.setFromCountry(row.getCell(0).getStringCellValue());
        peakId.setService(new Service(row.getCell(1).getStringCellValue()));

        peakInfo.setPeakStart(getTime(row.getCell(2).getNumericCellValue()));
        peakInfo.setOffPeakStart(getTime(row.getCell(3).getNumericCellValue()));
        peakInfo.setPeakId(peakId);
        return peakInfo;
    }

    private Date getTime(double time) {
        int minute = new Double(time % 100).intValue();
        int hour = new Double(time / 100).intValue();
//        System.out.println(time+":"+hour+":"+minute);
        Calendar cal = Calendar.getInstance();

        cal.set(1970, 1, 2, hour, minute);
        return cal.getTime();
    }
}
