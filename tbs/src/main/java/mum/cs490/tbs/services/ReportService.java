/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import java.io.File;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import mum.cs490.tbs.controller.UserBean;
import mum.cs490.tbs.dao.IGenericDaoII;
import mum.cs490.tbs.dao.impl.CallDetailDao;
import mum.cs490.tbs.dao.impl.GenericDaoII;
import mum.cs490.tbs.dao.impl.IReportDao;
import mum.cs490.tbs.model.CallDetail;
import mum.cs490.tbs.model.CallingRate;
import mum.cs490.tbs.report.Component;
import mum.cs490.tbs.report.DynamicComponentBuilder;
import mum.cs490.tbs.report.ExportService;
import mum.cs490.tbs.report.ReportUtil;
import mum.cs490.tbs.report.TableColumn;
import mum.cs490.tbs.report.TemplateProvider;
import mum.cs490.tbs.utility.FileUtility;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Autowired
    private CallDetailDao callDetailDao;

    public ReportService() {
        componentBuilder = new DynamicComponentBuilder();
        exportService = new ExportService();
    }

    @Override
    public String exportRateSheet(String basePath,String country, String service) {
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
            component.setBasePath(basePath);
            JasperReportBuilder table = 
                    componentBuilder.createTable(component, new JRBeanCollectionDataSource(callingRateList));
            table.setTemplateDesign(TemplateProvider.getTemplate(basePath+"/"+"report_layout/defaulttemplate.jrxml"));
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
            TextColumnBuilder<BigInteger> col1 = col.column("Phone Number", "toCustomerTelephoneNo", type.bigIntegerType());
            TextColumnBuilder<String> col2= col.column("Destination Country", "toCountry", type.stringType());
            TextColumnBuilder<Double> col3 = col.column("Duration", "duration", type.doubleType());
            TextColumnBuilder<Date> col4= col.column("Call Time", "callTime", type.dateYearToHourType());
            TextColumnBuilder<Double> col5= col.column("Cost", "amount", type.doubleType());
            columns = new ArrayList<TableColumn>();
            columns.add(createColumn("", col1,20, false, true));
            columns.add(createColumn("", col2,20, false, true));
            columns.add(createColumn("", col3,20, false, true));
            columns.add(createColumn("", col4,20, false, true));
             columns.add(createColumn("", col5,20, false, true));
           
            List<Map<String, Object>> callingRateList = reportDao.genCustomerBill(telephoneNo);
            System.out.println(callingRateList.size());
            Component component = new Component();
            component.setColumns(columns);
            JasperReportBuilder table = 
                    componentBuilder.createCustomerBillTable(component, new JRBeanCollectionDataSource(callingRateList));
            table.setTemplateDesign(TemplateProvider.getTemplate("report_layout/defaulttemplate.jrxml"));
            String outputPath = FileUtility.getServerFilePath("export");
            exportService.exportToPdf(table, outputPath, "CustomerBill");
            return outputPath + "/CustomerBill.pdf";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String generateTrafficSummary() {
         try {
            Collection<TableColumn> columns;
            TextColumnBuilder<BigInteger> col1 = col.column("Service Name", "toCustomerTelephoneNo", type.bigIntegerType());
            TextColumnBuilder<String> col2= col.column("Destination Country", "toCountry", type.stringType());
            TextColumnBuilder<Double> col3 = col.column("Source Country", "duration", type.doubleType());
            TextColumnBuilder<Date> col4= col.column("Total minutes of call", "callTime", type.dateYearToHourType());
            columns = new ArrayList<TableColumn>();
            columns.add(createColumn("", col1,20, false, true));
            columns.add(createColumn("", col2,20, false, true));
            columns.add(createColumn("", col3,20, false, true));
            columns.add(createColumn("", col4,20, false, true));
           
            List<Map<String, Object>> callingRateList = new ArrayList<>();
            System.out.println(callingRateList.size());
            Component component = new Component();
            component.setColumns(columns);
            JasperReportBuilder table = 
                    componentBuilder.createCustomerBillTable(component, new JRBeanCollectionDataSource(callingRateList));
            table.setTemplateDesign(TemplateProvider.getTemplate("report_layout/defaulttemplate.jrxml"));
            String outputPath = FileUtility.getServerFilePath("export");
            exportService.exportToPdf(table, outputPath, "TrafficSummary");
            return outputPath + "/TrafficSummary.pdf";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
