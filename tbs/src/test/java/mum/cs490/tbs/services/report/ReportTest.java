package mum.cs490.tbs.services.report;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import mum.cs490.tbs.model.CallingCodes;
import mum.cs490.tbs.model.CallingRate;
import mum.cs490.tbs.model.Customer;
import mum.cs490.tbs.model.PeakInfo;
import mum.cs490.tbs.model.Service;

import mum.cs490.tbs.report.ChartColumn;
import mum.cs490.tbs.report.Component;
import mum.cs490.tbs.report.ComponentException;
import mum.cs490.tbs.report.DataSource;
import mum.cs490.tbs.report.DynamicComponentBuilder;
import mum.cs490.tbs.report.ExporterService;
import mum.cs490.tbs.report.ReportUtil;
import mum.cs490.tbs.report.TableColumn;
import mum.cs490.tbs.report.TemplateProvider;
import mum.cs490.tbs.services.BaseTestCase;
import mum.cs490.tbs.services.CallingCodeReader;
import mum.cs490.tbs.services.CustomerInfoReader;
import mum.cs490.tbs.services.PeakInfoReader;
import mum.cs490.tbs.services.Reader;
import mum.cs490.tbs.utility.FileUtil;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReportTest extends BaseTestCase{

    private DynamicComponentBuilder componentBuilder = new DynamicComponentBuilder();
    private ReportUtil reportUtil = new ReportUtil();
    private ExporterService exporterService = new ExporterService();
    public static String testOutputPath = "components/";

    
//    @BeforeClass
//    public void beforeClass() throws IOException{
//        List<Service> services = new ArrayList<>();
//        Reader reader = new Reader();
//
//        reader.setReader(new PeakInfoReader());
//        Map<String, List<PeakInfo>> data3 = reader.read("data/Peak.xls");
//
//        for (PeakInfo peakInfo : data3.get("Sheet1")) {
//            services.add(peakInfo.getPeakId().getService());
//        }
//
//        updateService.storeServices(services);
//
//        updateService.storePeakInfo(data3);
//
//         reader.setReader(new CustomerInfoReader());
//        Map<String,List<Customer>> data4=reader.read("data/Customers.xls");
//        
//        
//        updateService.storeCustomers(data4.get("Sheet1"));
//      
//        reader.setReader(new CallingCodeReader());
//        Map<String,List<CallingCodes>> data=reader.read("data/calling_code.xls");
//        updateService.storeCallingCodes(data);
//    }
    private static ChartColumn createChartColumn(String name,
            TextColumnBuilder<?> textColumnBuilder, boolean isSeries, boolean isCategory) {

        ChartColumn column = new ChartColumn();
        column.setName(name);
        column.setTextColumnBuilder(textColumnBuilder);
        column.setData(isSeries);
        column.setLabel(isCategory);
        return column;
    }

    private static TableColumn createColumn(String name, TextColumnBuilder<?> textColumnBuilder,
            boolean isKey, boolean isSeries) {
        TableColumn column = new TableColumn();
        column.setName(name);
        column.setTextColumnBuilder(textColumnBuilder);
        return column;

    }

    private static JRDataSource createDataSource() {
        DataSource dataSource = new DataSource("Name", "Roll", "Marks");
        dataSource.add("user1 user1 user1 user1 user1 user1", 1, 50);
        dataSource.add("user2", 2, 25);
        dataSource.add("user3", 3, 75);
        dataSource.add("user4", 1, 50);
        dataSource.add("user5", 2, 25);
        dataSource.add("user6", 3, 75);
        dataSource.add("user7", 1, 50);
        dataSource.add("user8", 2, 25);
        dataSource.add("user9", 3, 75);
        dataSource.add("user10", 1, 50);
        dataSource.add("user11", 2, 25);
        dataSource.add("user12", 3, 75);
        dataSource.add("user13", 1, 50);
        dataSource.add("user14", 2, 25);
        dataSource.add("user15", 3, 75);
        dataSource.add("user16", 1, 50);
        dataSource.add("user17", 2, 25);
        dataSource.add("user18", 3, 75);
        return dataSource;
    }

    private static JRDataSource createHeaderDataSource() {
        DataSource dataSource = new DataSource("content");
        dataSource.add("This is test header.");
        return dataSource;
    }

    private static JRDataSource createParagraphDataSource() {
        DataSource dataSource = new DataSource("content");
        dataSource.add("This is test paragraph.");
        return dataSource;
    }

    private static JRDataSource createTimeSeriesDataSource() {
        DataSource dataSource = new DataSource("orderdate", "quantity", "price");
        dataSource.add(toDate(2010, 1), 50, new BigDecimal(200));
        dataSource.add(toDate(2010, 2), 110, new BigDecimal(450));
        dataSource.add(toDate(2010, 3), 70, new BigDecimal(280));
        dataSource.add(toDate(2010, 4), 250, new BigDecimal(620));
        dataSource.add(toDate(2010, 5), 100, new BigDecimal(400));
        dataSource.add(toDate(2010, 6), 80, new BigDecimal(320));
        dataSource.add(toDate(2010, 7), 180, new BigDecimal(490));
        return dataSource;
    }

    @BeforeClass
    public static void oneTimeSetUp() {
        FileUtil.deleteAndCreateDirectory(testOutputPath);
    }

    private static Date toDate(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        return c.getTime();
    }

    @Test
    public void createRateSheet() throws ComponentException, FileNotFoundException, DRException {
            // create rate sheet destination country name, peak rate, off-peak rate: Spectra_France
        // rate sheet: destination countries are listed in alphabetical order, rates on single page, page header showing telephone company information, service namre, from country and date, page footer showing starting times for peak and off-peak periods
        // create customer bills: phone number, destination country name, length of call, time of call, cost of the call, amount due, header has name, street address, city, state, zip, code, and country, telephone number, header has company logo
        // generate sales rep commission information
        // generate traffic summary excel sheet with four coulumns service name, from country name, to country name, total minutes of call

        Collection<TableColumn> columns;

        JRDataSource dataSource;

        TextColumnBuilder<String> userColumn = col.column("Destination Country", "Destination Country", type.stringType());
        TextColumnBuilder<Integer> rollColumn = col.column("Peak", "Peak", type.integerType());
        TextColumnBuilder<Integer> marksColumn = col.column("Off Peak", "Off Peak", type.integerType());
        columns = new ArrayList<TableColumn>();
        columns.add(createColumn("", userColumn, false, true));
        columns.add(createColumn("", rollColumn, true, false));
        columns.add(createColumn("", marksColumn, true, false));

        List<CallingRate> callingRateList=reportService.getRateList("USA", "Spectra");
        System.out.println(callingRateList.size());
        dataSource = createDataSource();
        Component component = new Component();
        component.setColumns(columns);
        JasperReportBuilder table = reportUtil.getDynamicReport(
                componentBuilder.createTable(component, dataSource), new JREmptyDataSource());
        table.setTemplateDesign(TemplateProvider.getTemplate("report_layout/defaulttemplate.jrxml"));
        exporterService.exportToPdf(table, testOutputPath, "RateSheet");
        Assert.assertTrue(FileUtil.fileExists(testOutputPath + "RateSheet.pdf"));
    }

}
