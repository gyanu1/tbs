/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.utility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import javax.servlet.http.Part;
import mum.cs490.tbs.controller.UserBean;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author gyanu
 */
public class FileUtility {

    static Logger log = Logger.getLogger(UserBean.class.getName());

    public static File saveUploadedFile(String folder, Part file) {
        boolean saved = false;
        File serverFile = null;
        if (file.getSize() > 0) {
            try {
                InputStream input = file.getInputStream();
                //creating the directory to store file
                ClassLoader c = FileUtility.class.getClassLoader();
                URLClassLoader u = (URLClassLoader) c;
                URL[] urls = u.getURLs();
                String path = "";
                for (URL i : urls) {
                    if (i.toString().contains("WEB-INF")) {
                        path = i.toString();
                        log.info("url: " + i);
                        break;
                    }
                }
                String tokens[] = path.split("WEB-INF");
                path = tokens[0];
                path = path.replaceFirst("file:", "");
                File fileDir = new File(path + File.separator + "uploads" + File.separator + folder);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                //create the file on server
                serverFile = new File(fileDir.getAbsolutePath() + File.separator + file.getSubmittedFileName());

                try (BufferedOutputStream fileStream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                    fileStream.write(IOUtils.toByteArray(input));
                }
                saved = true;
                log.info("You successfully uploaded " + file.getSubmittedFileName());
            } catch (Exception e) {
                log.info(" Failed to upload ");
            }
        } else {
            log.info("Failed due to  empty upload file.");
        }
        return serverFile;
    }

    public static String getServerFilePath(String folder) {
        ClassLoader c = FileUtility.class.getClassLoader();
        URLClassLoader u = (URLClassLoader) c;
        URL[] urls = u.getURLs();
        String path = "";
        for (URL i : urls) {
            if (i.toString().contains("WEB-INF")) {
                path = i.toString();
                log.info("url: " + i);
                break;
            }
        }
        String tokens[] = path.split("WEB-INF");
        path = tokens[0];
        path = path.replaceFirst("file:", "");
        File fileDir = null;
        if (path.isEmpty()) {
            fileDir = new File("uploads" + File.separator + folder);
        } else {
            fileDir = new File(path + File.separator + "uploads" + File.separator + folder);
        }
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return fileDir.getAbsolutePath();
    }

}
