/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import mum.cs490.tbs.services.IUpdateService;
import mum.cs490.tbs.services.Reader;
import mum.cs490.tbs.services.ReaderFactory;
import mum.cs490.tbs.utility.FileType;
import mum.cs490.tbs.utility.FileUtility;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author GMaharjan
 */
@Named
@SessionScoped
public class FileUploadBean implements Serializable {

    private Part file;
    static Logger log = Logger.getLogger(FileUploadBean.class.getName());
    private FileType fileType;
    @Autowired
    private IUpdateService updateService;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public void upload() {
        Reader reader = new Reader();
        log.info("inside file upload : " + file.getSubmittedFileName());
        log.info("file type selected : " + fileType);
        if (file != null && file.getSize() > 0) {
            try {
                log.info("file" + file.getContentType());
                File fileSaved = FileUtility.saveUploadedFile(fileType.toString().toLowerCase(), file);
                reader.setReader(ReaderFactory.getReader(fileType));
                System.out.println(fileSaved.getAbsolutePath());
                switch (fileType) {
                    case RATE:
                        updateService.updateRate(reader.read(fileSaved.getAbsolutePath()));
                        break;
                    case CALL_DETAIL:
                        updateService.storeCallDetails(reader.read(fileSaved.getAbsolutePath()));
                        break;
                }

            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(FileUploadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public FileType[] getFileTypes() {
        return FileType.values();
    }
}
