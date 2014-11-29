/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import mum.cs490.tbs.controller.UserBean;
import mum.cs490.tbs.dao.impl.IReportDao;
import mum.cs490.tbs.model.CallingRate;
import mum.cs490.tbs.report.Component;
import mum.cs490.tbs.report.DynamicComponentBuilder;
import mum.cs490.tbs.report.ExportService;
import mum.cs490.tbs.report.ReportUtil;
import mum.cs490.tbs.report.TableColumn;
import mum.cs490.tbs.report.TemplateProvider;
import mum.cs490.tbs.utility.FileUtility;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

/**
 *
 * @author PuKhanal
 */
@Service("reportService")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ReportService implements IReportService {

    public static Logger log = org.apache.log4j.Logger.getLogger(UserBean.class.getName());
    @Autowired
    private IReportDao reportDao;

    private DynamicComponentBuilder componentBuilder;

    private ExportService exportService;

    public ReportService() {
        componentBuilder = new DynamicComponentBuilder();
        exportService = new ExportService();
    }

    @Override
    public String exportRateSheet(String country, String service) {
        try {
            Collection<TableColumn> columns;
            TextColumnBuilder<String> destinationCountry = col.column("Destination Country", "id.destinationCountry.country", type.stringType());
            TextColumnBuilder<Double> peak = col.column("Peak", "peak", type.doubleType());
            TextColumnBuilder<Double> offPeak = col.column("Off Peak", "offPeak", type.doubleType());
            columns = new ArrayList<TableColumn>();
            columns.add(createColumn("", destinationCountry,20, false, true));
            columns.add(createColumn("", peak,20, true, false));
            columns.add(createColumn("", offPeak,20, true, false));
            List<CallingRate> callingRateList = reportDao.getRateList(country, service);
            System.out.println(callingRateList.size());
            Component component = new Component();
            component.setColumns(columns);
            JasperReportBuilder table = 
                    componentBuilder.createTable(component, new JRBeanCollectionDataSource(callingRateList));
            table.setTemplateDesign(TemplateProvider.getTemplate("report_layout/defaulttemplate.jrxml"));
            String outputPath = FileUtility.getServerFilePath("export");
            exportService.exportToPdf(table, outputPath, "RateSheet");
            return outputPath + "/RateSheet.pdf";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    

    private static TableColumn createColumn(String name, TextColumnBuilder<?> textColumnBuilder,int width,
            boolean isKey, boolean isSeries) {
        TableColumn column = new TableColumn();
        column.setName(name);
        column.setTextColumnBuilder(textColumnBuilder);
        column.setWidth(width);
        return column;

    }

    @Override
    public String generateCustomerBill(Long telephoneNo) {
        
        try {
            Collection<TableColumn> columns;
            TextColumnBuilder<String> destinationCountry = col.column("Destination Country", "id.destinationCountry.country", type.stringType());
            TextColumnBuilder<Double> peak = col.column("Peak", "peak", type.doubleType());
            TextColumnBuilder<Double> offPeak = col.column("Off Peak", "offPeak", type.doubleType());
            columns = new ArrayList<TableColumn>();
            columns.add(createColumn("", destinationCountry,20, false, true));
            columns.add(createColumn("", peak,20, true, false));
            columns.add(createColumn("", offPeak,20, true, false));
//            List<CallingRate> callingRateList = reportDao.getRateList(country, service);
//            System.out.println(callingRateList.size());
            Component component = new Component();
            component.setColumns(columns);
            JasperReportBuilder table = 
                    componentBuilder.createTable(component, new JREmptyDataSource());
            table.setTemplateDesign(TemplateProvider.getTemplate("report_layout/defaulttemplate.jrxml"));
            String outputPath = FileUtility.getServerFilePath("export");
            exportService.exportToPdf(table, outputPath, "RateSheet");
            return outputPath + "/RateSheet.pdf";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String generateTrafficSummary() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
