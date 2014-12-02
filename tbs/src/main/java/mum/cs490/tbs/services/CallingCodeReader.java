/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import mum.cs490.tbs.model.CallingCodes;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author puneetkhanal
 */
public class CallingCodeReader extends ExcelReader<CallingCodes> {

    @Override
    public CallingCodes getRow(Row row, String sheetName) {
        String rawCountry=row.getCell(0).getStringCellValue();
        if(rawCountry.equalsIgnoreCase("United States of America")){
            rawCountry="USA";
        }
        CallingCodes country = new CallingCodes(rawCountry, new Double(row.getCell(1).getNumericCellValue()).intValue());
        return country;
    }

}
