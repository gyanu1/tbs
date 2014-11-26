/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author GMaharjan
 */
@Named
@SessionScoped
public class RatesBean implements Serializable{
   private UploadedFile file;
    static Logger log = Logger.getLogger(RatesBean.class.getName());

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void save(){
        log.info("inside method save");
         log.info("file name : "+file.getFileName());
    }
    
}
