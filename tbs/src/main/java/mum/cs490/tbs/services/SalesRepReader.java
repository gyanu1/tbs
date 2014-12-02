/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mum.cs490.tbs.services;

import java.util.HashMap;
import java.util.Map;
import mum.cs490.tbs.model.TbsUser;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author puneetkhanal
 */
public class SalesRepReader extends ExcelReader<SalesRep> {

    
    
    
    private Map<Long,SalesRep> salesRepMap=new HashMap<Long, SalesRep>();
    @Override
    public SalesRep getRow(Row row, String sheetName) {
        SalesRep salesRep=new SalesRep();
        salesRep.setTelephoneNo(new Double(row.getCell(0).getNumericCellValue()).longValue());
        salesRep.setId(new Double(row.getCell(1).getNumericCellValue()).intValue());
        salesRep.setCommission(new Double(row.getCell(2).getNumericCellValue()).intValue());
        
        salesRepMap.put(salesRep.getTelephoneNo(), salesRep);
        return salesRep;
       
    }

    public Map<Long, SalesRep> getSalesRepMap() {
        return salesRepMap;
    }
    
    
}
