/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import java.util.Date;
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

    @Override
    public CallingRate getRow(Row row, String sheetName) {
        CallingRate callingRate = new CallingRate();
        RateId rateId = new RateId();
        rateId.setDestinationCountry(new CallingCodes(null, new Double(row.getCell(0).getNumericCellValue()).intValue()));
        rateId.setUpdateDate(new Date(System.currentTimeMillis()));
        String[] split = sheetName.split("_");
        rateId.setService(new Service(split[0]));
        rateId.setSheetName(sheetName);
        callingRate.setId(rateId);
        callingRate.setPeak(row.getCell(1).getNumericCellValue());
        callingRate.setOffPeak(row.getCell(2).getNumericCellValue());
        return callingRate;
    }

}
