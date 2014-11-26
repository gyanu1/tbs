/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import mum.cs490.tbs.model.CallDetail;
import mum.cs490.tbs.model.CallingCodes;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author PuKhanal
 */
public class CallDetailsReader extends ExcelReader<CallDetail> {

    @Override
    public CallDetail getRow(Row row) {
        CallDetail callDetail = new CallDetail();
        callDetail.setFromCountry(new CallingCodes(null, new Double(row.getCell(0).getNumericCellValue()).intValue()));
        callDetail.setToCountry(new CallingCodes(null, new Double(row.getCell(1).getNumericCellValue()).intValue()));
//         callDetail.set
        return callDetail;
    }

}
