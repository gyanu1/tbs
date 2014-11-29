package mum.cs490.tbs.report;

import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.*;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.log4j.Logger;

import static net.sf.dynamicreports.report.builder.DynamicReports.export;

public class ExportService {

    private Logger logger = Logger.getLogger(ExportService.class.getName());


    public void exportToDocx(JasperConcatenatedReportBuilder report, String location, String name) {
        try {
            report.toDocx(getDocxExporter(location, name));
            logSuccessMessage(name, "docx");
        } catch (DRException e) {
            e.printStackTrace();
            logErrorMessage(name, "docx", e.getMessage());
        }

    }

    public void exportToDocx(JasperReportBuilder report, String location, String name) {
        try {
            report.toDocx(getDocxExporter(location, name));
            logSuccessMessage(name, "docx");
        } catch (DRException e) {
            e.printStackTrace();
            logErrorMessage(name, "docx", e.getMessage());
        }

    }

    public void exportToHtml(JasperConcatenatedReportBuilder report, String location, String name) {
        try {
            report.toXhtml(getXHtmlExporter(location, name));
            logSuccessMessage(name, "html");
        } catch (DRException e) {
            e.printStackTrace();
            logErrorMessage(name, "html", e.getMessage());
        }

    }

    public void exportToHtml(JasperConcatenatedReportBuilder report, String location, String name,
            String generatedReportsLocation) {
        try {
            report.toXhtml(getXHtmlExporter(location, name, generatedReportsLocation));
            logSuccessMessage(name, "html");
        } catch (DRException e) {
            e.printStackTrace();
            logErrorMessage(name, "html", e.getMessage());
        }

    }

    public void exportToHtml(JasperReportBuilder report, String location, String name) {

        try {
            report.toXhtml(getXHtmlExporter(location, name));
            logSuccessMessage(name, "html");
        } catch (DRException e) {
            e.printStackTrace();
            logErrorMessage(name, "html", e.getMessage());
        }

    }

    public void exportToPdf(JasperConcatenatedReportBuilder report, String location, String name) {
        try {
            report.toPdf(getPdfExporter(location, name));
            logSuccessMessage(name, "pdf");
        } catch (DRException e) {
            e.printStackTrace();
            logErrorMessage(name, "pdf", e.getMessage());
        }
    }

    public void exportToPdf(JasperConcatenatedReportBuilder report, String location, String name,
            String password) {
        try {
            report.toPdf(getPdfExporter(location, name, password));
            logSuccessMessage(name, "pdf");
        } catch (DRException e) {
            e.printStackTrace();
            logErrorMessage(name, "pdf", e.getMessage());
        }
    }

    public void exportToPdf(JasperReportBuilder report, String location, String name) {
        try {
            report.toPdf(getPdfExporter(location, name));
            logSuccessMessage(name, "pdf");
        } catch (DRException e) {
            e.printStackTrace();
            logErrorMessage(name, "pdf", e.getMessage());
        }
    }

    public void exportToPdf(JasperReportBuilder report, String location, String name,
            String password) {
        try {
            report.toPdf(getPdfExporter(location, name, password));
            logSuccessMessage(name, "pdf");
        } catch (DRException e) {
            e.printStackTrace();
            logErrorMessage(name, "pdf", e.getMessage());
        }
    }

   
    public void exportToXls(JasperConcatenatedReportBuilder report, String location, String name) {
        try {
            report.toXls(getXlsExporter(location, name));
            logSuccessMessage(name, "excel");
        } catch (DRException e) {
            e.printStackTrace();
            logErrorMessage(name, "excel", e.getMessage());
        }
    }

    public void exportToXls(JasperReportBuilder report, String location, String name) {
        try {
            report.toXls(getXlsExporter(location, name));
            logSuccessMessage(name, "xls");
        } catch (DRException e) {
            e.printStackTrace();
            logErrorMessage(name, "xls", e.getMessage());
        }
    }


    private JasperDocxExporterBuilder getDocxExporter(String location, String name) {
        JasperDocxExporterBuilder docxExporter = export.docxExporter(
                location + "/" + name + ".docx").setFlexibleRowHeight(false);
        return docxExporter;
    }

    private JasperPdfExporterBuilder getPdfExporter(String location, String name) {
        JasperPdfExporterBuilder pdfExporter = export.pdfExporter(location + "/" + name + ".pdf");
        return pdfExporter;
    }

    private JasperPdfExporterBuilder getPdfExporter(String location, String name, String password) {
        JasperPdfExporterBuilder pdfExporter = export.pdfExporter(location + "/" + name + ".pdf")
                .setEncrypted(true).setUserPassword(password);
        return pdfExporter;
    }
    
    private JasperXhtmlExporterBuilder getXHtmlExporter(String location, String name) {
        JasperXhtmlExporterBuilder xHtmlExporter = export
                .xhtmlExporter(location + "/" + name + ".html")
                .setImagesURI(location + "/" + name + "/")
                .setImagesDirName(location + "/" + name + "/").setOutputImagesToDir(true);
        return xHtmlExporter;
    }

    private JasperXhtmlExporterBuilder getXHtmlExporter(String location, String name,
            String generatedReportsLocation) {
        JasperXhtmlExporterBuilder xHtmlExporter = export
                .xhtmlExporter(location + "/" + name + ".html")
                .setImagesURI(generatedReportsLocation + name + "/")
                .setImagesDirName(location + "/" + name + "/").setOutputImagesToDir(true);
        return xHtmlExporter;
    }

    private JasperXlsExporterBuilder getXlsExporter(String location, String name) {
        JasperXlsExporterBuilder excelExporter = export.xlsExporter(location + "/" + name + ".xls")
                .setRemoveEmptySpaceBetweenColumns(true).setFontSizeFixEnabled(true)
                .setCollapseRowSpan(false);
        return excelExporter;
    }

    private void logErrorMessage(String name, String type, String message) {
        logger.error(this.getClass().getSimpleName() + ";" + "export to " + type + ";"
                + "msg=unable to export " + name + " to" + type);
    }

    private void logSuccessMessage(String name, String type) {
        logger.info(this.getClass().getSimpleName() + ";" + "export to " + type + ";" + "msg="
                + name + " successfully exported to " + type);
    }
}
