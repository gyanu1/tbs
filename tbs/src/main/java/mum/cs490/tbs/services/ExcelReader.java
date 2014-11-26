/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author puneetkhanal
 */
public abstract class ExcelReader<T> {

    public Map<String, List<T>> loadWorkBook(String filepath) throws FileNotFoundException, IOException {
        FileInputStream file = new FileInputStream(new File(filepath));
        //Get the workbook instance for XLS file 
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        Map<String, List<T>> sheetRows = new HashMap<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            HSSFSheet sheet = workbook.getSheetAt(i);
            if (!sheetRows.containsKey(sheet.getSheetName())) {
                sheetRows.put(sheet.getSheetName(), new ArrayList<T>());
            }
            //Iterate through each rows from first sheets
            Iterator<Row> rowIterator = sheet.iterator();
            boolean firstRow=true;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                if(!firstRow){
                    sheetRows.get(sheet.getSheetName()).add(getRow(row));
                }else{
                    firstRow=false;
                }
            }
        }
        file.close();
        return sheetRows;
    }

    public abstract T getRow(Row row) ;
}
