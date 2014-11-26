/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author puneetkhanal
 */
public class CountryCodeReader extends ExcelReader<Country> {

    @Override
    public Country getRow(Row row) {
        Country country = new Country(row.getCell(0).getStringCellValue(), new Double(row.getCell(1).getNumericCellValue()).intValue());
        return country;
    }

}
