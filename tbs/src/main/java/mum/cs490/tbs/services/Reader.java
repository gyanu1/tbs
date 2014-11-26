/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import java.io.IOException;
import java.util.Map;

/**
 *
 * @author PuKhanal
 */
public class Reader {
    
    private ExcelReader excelReader;
    
    public Map read(String filePath) throws IOException{
        return excelReader.loadWorkBook(filePath);
    }
    public void setReader(ExcelReader excelReader){
        this.excelReader=excelReader;
    }
}
