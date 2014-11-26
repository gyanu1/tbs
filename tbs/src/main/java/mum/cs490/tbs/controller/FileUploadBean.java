/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import mum.cs490.tbs.utility.FileType;
import mum.cs490.tbs.utility.FileUtility;
import org.apache.log4j.Logger;

/**
 *
 * @author GMaharjan
 */
@Named
@SessionScoped
public class FileUploadBean implements Serializable{
   private Part file;
    static Logger log = Logger.getLogger(FileUploadBean.class.getName());
    private FileType fileType;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    public void upload() {
        log.info("inside file upload : " + file.getSubmittedFileName());
        log.info("file type selected : "+fileType);
        if (file != null && file.getSize() > 0) {
            log.info("file" + file.getContentType());
            FileUtility.saveUploadedFile(fileType.toString().toLowerCase(), file);
        }
     
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
    
    public FileType[] getFileTypes(){
        return FileType.values();
    }
}
