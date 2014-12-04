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
import mum.cs490.tbs.dao.IGenericDao;
import mum.cs490.tbs.dao.IGenericDaoII;
import mum.cs490.tbs.dao.impl.CallDetailDao;
import mum.cs490.tbs.dao.impl.CustomerDao;
import mum.cs490.tbs.dao.impl.GenericDaoII;
import mum.cs490.tbs.dao.impl.IReportDao;
import mum.cs490.tbs.model.CallDetail;
import mum.cs490.tbs.model.CallingRate;
import mum.cs490.tbs.model.Customer;
import mum.cs490.tbs.model.PeakInfo;
import mum.cs490.tbs.report.Component;
import mum.cs490.tbs.report.DurationFormatter;
import mum.cs490.tbs.report.DynamicComponentBuilder;
import mum.cs490.tbs.report.ExportService;
import mum.cs490.tbs.report.NumberToStringFormatter;
import mum.cs490.tbs.report.ReportUtil;
import mum.cs490.tbs.report.TableColumn;
import mum.cs490.tbs.report.TemplateProvider;
import mum.cs490.tbs.report.Templates;
import mum.cs490.tbs.utility.FileUtility;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.sbt;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
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

    @Autowired
    private CustomerDao customerDao;

    public ReportService() {
        componentBuilder = new DynamicComponentBuilder();
        exportService = new ExportService();
    }

    @Override
    public String exportRateSheet(String basePath, String country, String service) {
        try {
            Collection<TableColumn> columns;
            TextColumnBuilder<String> destinationCountry = col.column("Destination Country", "id.destinationCountry.country", type.stringType());
            TextColumnBuilder<Double> peak = col.column("Peak", "peak", type.doubleType()).setPattern("0.00");
            TextColumnBuilder<Double> offPeak = col.column("Off Peak", "offPeak", type.doubleType()).setPattern("0.00");
            columns = new ArrayList<TableColumn>();
            columns.add(createColumn("", destinationCountry, 20, false));
            columns.add(createColumn("", peak, 20, false));
            columns.add(createColumn("", offPeak, 20, false));
            List<CallingRate> callingRateList = reportDao.getRateList(country, service);
            System.out.println();
            Component component = new Component();
            component.setColumns(columns);
            component.setBasePath(basePath);
            PeakInfo peakInfo = reportDao.getPeakInfo(country, service);
            JasperReportBuilder table
                    = componentBuilder.createRateSheetTable(country, service, peakInfo, callingRateList.get(0).getId().getUpdateDate(), component, new JRBeanCollectionDataSource(callingRateList));
            table.setTemplateDesign(TemplateProvider.getTemplate(basePath + "/" + "report_layout/ratesheet.jrxml"));

            JasperReportBuilder excel
                    = componentBuilder.createExcelRateSheetTable(component, new JRBeanCollectionDataSource(callingRateList));

            String outputPath = FileUtility.getServerFilePath("export");
            exportService.exportToPdf(table, outputPath, service+"_"+country);
            exportService.exportToXls(excel, outputPath, service+"_"+country);
            return service+"_"+country;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static TableColumn createColumn(String name, TextColumnBuilder<?> textColumnBuilder, int width, boolean isAggregate) {
        TableColumn column = new TableColumn();
        column.setName(name);
        column.setTextColumnBuilder(textColumnBuilder);
        column.setWidth(width);
        column.setAggregatecolumn(isAggregate);
        return column;

    }

    @Override
    public String generateCustomerBill(String basepath, Long telephoneNo, Date date) {
        try {
            Collection<TableColumn> columns;
            TextColumnBuilder<BigInteger> col1 = col.column("Phone Number", "toCustomerTelephoneNo", type.bigIntegerType()).setValueFormatter(new NumberToStringFormatter());
            TextColumnBuilder<String> col2 = col.column("Destination Country", "toCountry", type.stringType());
            TextColumnBuilder<Double> col3 = col.column("Duration", "duration", type.doubleType()).setValueFormatter(new DurationFormatter());
            TextColumnBuilder<Date> col4 = col.column("Call Time", "callTime", type.dateYearToHourType()).setPattern("hh:mm:ss");
            TextColumnBuilder<Double> col5 = col.column("Cost", "amount", type.doubleType()).setPattern("0.00");
            columns = new ArrayList<TableColumn>();
            columns.add(createColumn("", col1, 20, false));
            columns.add(createColumn("", col2, 20, false));
            columns.add(createColumn("", col3, 20, false));
            columns.add(createColumn("", col4, 20, false));
            columns.add(createColumn("", col5, 20, true));

            List<Map<String, Object>> callingRateList = reportDao.genCustomerBill(telephoneNo, date);
            System.out.println(callingRateList.size());
            Component component = new Component();
            component.setColumns(columns);
            component.setBasePath(basepath);
            Customer customer = customerDao.getByID(telephoneNo);
            JasperReportBuilder table
                    = componentBuilder.createCustomerBillTable(component, date, customer, new JRBeanCollectionDataSource(callingRateList));
            table.setTemplateDesign(TemplateProvider.getTemplate(basepath + "/" + "report_layout/customerbill.jrxml"));

            JasperReportBuilder excelReport
                    = componentBuilder.createExcelRateSheetTable(component, new JRBeanCollectionDataSource(callingRateList));
            table.setTemplateDesign(TemplateProvider.getTemplate(basepath + "/" + "report_layout/customerbill.jrxml"));
            String outputPath = FileUtility.getServerFilePath("export");
            exportService.exportToPdf(table, outputPath, "CustomerBill");
            exportService.exportToXls(excelReport, outputPath, "CustomerBill");
            return outputPath + "/CustomerBill.pdf";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String generateTrafficSummary(String basePath, Date date) {
        try {
            Collection<TableColumn> columns;
            TextColumnBuilder<String> col1 = col.column("Service Name", "service", type.stringType());
            TextColumnBuilder<String> col2 = col.column("Source Country", "sourceCountry", type.stringType());
            TextColumnBuilder<String> col3 = col.column("Destination Country", "destinationCountry", type.stringType());
            TextColumnBuilder<Double> col4 = col.column("Total minutes of call", "total_mins", type.doubleType()).setValueFormatter(new NumberToStringFormatter());
            columns = new ArrayList<TableColumn>();
            columns.add(createColumn("", col1, 20, false));
            columns.add(createColumn("", col2, 20, false));
            columns.add(createColumn("", col3, 20, false));
            columns.add(createColumn("", col4, 20, false));

            List<Map<String, Object>> callingRateList = reportDao.genMonthlyTrafficSummary(date);

            Component component = new Component();
            component.setBasePath(basePath);
            component.setColumns(columns);
            JasperReportBuilder table
                    = componentBuilder.createTrafficSummaryTable(component, date, new JRBeanCollectionDataSource(callingRateList));
            table.setTemplateDesign(TemplateProvider.getTemplate(basePath + "/" + "report_layout/trafficsummary.jrxml"));
            JasperReportBuilder excelReport
                    = componentBuilder.createTrafficSummaryTable(component, date, new JRBeanCollectionDataSource(callingRateList));
            String outputPath = FileUtility.getServerFilePath("export");
            exportService.exportToPdf(table, outputPath, "TrafficSummary");
            exportService.exportToXls(excelReport, outputPath, "TrafficSummary");
            return "/TrafficSummary.pdf";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String generateSalesCommissionReport(String basePath, Date date) {
        try {
            Collection<TableColumn> columns;
            TextColumnBuilder<String> col0 = col.column("Customer Name", "name", type.stringType());
            TextColumnBuilder<BigInteger> col1 = col.column("Sales Rep Id", "salesRep_id", type.bigIntegerType());
            TextColumnBuilder<Double> col2 = col.column("Total Commission", "commission", type.doubleType()).setPattern("0.00");
    
            ColumnGroupBuilder repIdGroup = grp.group("Sales Rep Id",col1).groupByDataType();

            columns = new ArrayList<TableColumn>();
            columns.add(createColumn("", col0, 20, false));
            columns.add(createColumn("", col1, 20, false));
            columns.add(createColumn("", col2, 20, false));

            List<Map<String, Object>> callingRateList = reportDao.getSalesReport(date);

            Component component = new Component();
            component.setBasePath(basePath);
            component.setColumns(columns);
            JasperReportBuilder table
                    = componentBuilder.createTrafficSummaryTable(component, date, new JRBeanCollectionDataSource(callingRateList));
            table.setTemplateDesign(TemplateProvider.getTemplate(basePath + "/" + "report_layout/sales.jrxml"));
            table.groupBy(repIdGroup);
            table.subtotalsAtGroupFooter(repIdGroup, sbt.sum(col2));
            table.setSubtotalStyle(Templates.columnStyle);

            JasperReportBuilder excelReport
                    = componentBuilder.createTrafficSummaryTable(component, date, new JRBeanCollectionDataSource(callingRateList));
            String outputPath = FileUtility.getServerFilePath("export");
            exportService.exportToPdf(table, outputPath, "SalesRepReport");
            exportService.exportToXls(excelReport, outputPath, "SalesRepReport");
            return "/TrafficSummary.pdf";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
