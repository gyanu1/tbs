/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import mum.cs490.tbs.model.CallingCodes;
import mum.cs490.tbs.model.CallingRate;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author PuKhanal
 */
public class RateReader extends ExcelReader<CallingRate> {

    @Override
    public CallingRate getRow(Row row) {
        CallingRate callingRate = new CallingRate();
        callingRate.setDestinationCountry(new CallingCodes(null, new Double(row.getCell(0).getNumericCellValue()).intValue()));
        callingRate.setPeakRate(row.getCell(1).getNumericCellValue());
        callingRate.setOffPeakRate(row.getCell(2).getNumericCellValue());
        return callingRate;
    }

}
