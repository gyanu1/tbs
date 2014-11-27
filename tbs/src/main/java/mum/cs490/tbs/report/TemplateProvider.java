package mum.cs490.tbs.report;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TemplateProvider {

    private static Logger logger = Logger.getLogger(TemplateProvider.class.getName());

    public static JasperDesign getJasperDesign(String filePath) throws FileNotFoundException,
            JRException {
        JasperDesign jasperDesign = null;
        jasperDesign = JRXmlLoader.load(getTemplate(filePath));
        return jasperDesign;
    }

    public static JasperDesign getJasperDesignFromClasspath(String templateLocation)
            throws FileNotFoundException, JRException {
        JasperDesign jasperDesign = null;
        jasperDesign = JRXmlLoader.load(getTemplateFromClasspath(templateLocation));
        return jasperDesign;
    }

    public static InputStream getTemplate(String filePath) throws FileNotFoundException {
        return new FileInputStream(new File(filePath));
    }

    public static InputStream getTemplateFromClasspath(String classpath)
            throws FileNotFoundException {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(classpath);
        if (is == null) {
            throw new FileNotFoundException(classpath + " not found");
        }
        return ClassLoader.getSystemClassLoader().getResourceAsStream(classpath);
    }

    public static void setReportTemplate(JasperReportBuilder report, String templateFilePath) {
        try {
            report.setTemplateDesign(getTemplate(templateFilePath));
        } catch (Exception e) {
            logger.error("TemplateProvider" + ";setReportTemplate;msg=" + e.getMessage());
        }
    }
}
