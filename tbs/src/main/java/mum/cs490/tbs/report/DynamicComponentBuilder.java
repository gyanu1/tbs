package mum.cs490.tbs.report;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import mum.cs490.tbs.model.Customer;
import mum.cs490.tbs.model.PeakInfo;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.Position;
import net.sf.jasperreports.engine.JRDataSource;
import org.apache.log4j.Logger;

@SuppressWarnings("unchecked")
public class DynamicComponentBuilder {

    private Logger logger = Logger.getLogger(DynamicComponentBuilder.class.getName());

    public DynamicComponentBuilder() {
    }

    public JasperReportBuilder createRateSheetTable(String service, String country, PeakInfo peakInfo, Date date, Component component, JRDataSource dataSource)
            throws ComponentException {

//		setColumns(component);
        Collection<TableColumn> columns = (Collection<TableColumn>) component.getColumns();
        JasperReportBuilder table;
        table = report();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        table.setPageMargin(DynamicReports.margin(20));
        table.setParameter("realPath", component.getBasePath());
        table.setPageColumnsPerPage(2);
        table.setParameter("service", service);
        table.addParameter("service", String.class);
        table.setParameter("country", country);
        table.addParameter("country", String.class);
        table.setParameter("date", sdf.format(date));
        table.addParameter("date", String.class);
        if (peakInfo != null) {
            table.setParameter("peak", peakInfo.getPeakStart().toString());
            table.addParameter("peak", String.class);
            table.setParameter("offpeak", peakInfo.getOffPeakStart().toString());
            table.addParameter("offpeak", String.class);
        }

        table.setPageColumnSpace(10);
        table.setTemplate(Templates.reportTemplate);

        for (TableColumn column : columns) {

            table.addColumn(column.getTextColumnBuilder());
//            column.getTextColumnBuilder().setWidth(column.getWidth());
        }

        table.setDataSource(dataSource);

        return table;

    }

    public JasperReportBuilder createExcelRateSheetTable(Component component, JRDataSource dataSource)
            throws ComponentException {

//		setColumns(component);
        Collection<TableColumn> columns = (Collection<TableColumn>) component.getColumns();
        JasperReportBuilder table;
        table = report();
        table.setPageMargin(DynamicReports.margin(20));
        table.setParameter("realPath", component.getBasePath());
        table.setColumnTitleStyle(Templates.excelColumnStyle).ignorePageWidth().ignorePagination();
        table.setColumnStyle(Templates.excelColumnStyle);
//        table.setTemplate(Templates.reportTemplate);

        for (TableColumn column : columns) {

            table.addColumn(column.getTextColumnBuilder());
//            column.getTextColumnBuilder().setWidth(column.getWidth());
        }

        table.setDataSource(dataSource);

        return table;

    }

    public JasperReportBuilder createTrafficSummaryTable(Component component, Date date, JRDataSource dataSource)
            throws ComponentException {

//		setColumns(component);
        Collection<TableColumn> columns = (Collection<TableColumn>) component.getColumns();
        JasperReportBuilder table;
        table = report();
        table.setPageMargin(DynamicReports.margin(20));
        table.setParameter("realPath", component.getBasePath());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        table.setParameter("date", sdf.format(date));
        table.addParameter("date", String.class);
        table.setTemplate(Templates.reportTemplate);

        for (TableColumn column : columns) {

            table.addColumn(column.getTextColumnBuilder());
//            column.getTextColumnBuilder().setWidth(column.getWidth());
        }

        table.setDataSource(dataSource);

        return table;

    }

    public JasperReportBuilder createCustomerBillTable(Component component, Date date, Customer customer, JRDataSource dataSource)
            throws ComponentException {
        Collection<TableColumn> columns = (Collection<TableColumn>) component.getColumns();
        JasperReportBuilder table;
        table = report();

        table.setPageMargin(DynamicReports.margin(20));
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        table.setParameter("date", sdf.format(date));
        table.addParameter("date", String.class);
        table.setParameter("realPath", component.getBasePath());
        table.setParameter("fname", customer.getFirstname());
        table.addParameter("fname", String.class);
        table.setParameter("lname", customer.getLastname());
        table.addParameter("lname", String.class);
        table.setParameter("zip", customer.getZip());
        table.addParameter("zip", String.class);
        table.setParameter("city", customer.getCity());
        table.addParameter("city", String.class);
        table.setParameter("state", customer.getState());
        table.addParameter("state", String.class);
        table.setParameter("country", customer.getCountry());
        table.addParameter("country", String.class);
        table.setParameter("telephone", customer.getTelephoneNumber() + "");
        table.addParameter("telephone", String.class);
        table.setParameter("street", customer.getStreet());
        table.addParameter("street", String.class);

        table.setTemplate(Templates.reportTemplate);

        for (TableColumn column : columns) {
            if (column.isAggregatecolumn()) {

                table.subtotalsAtColumnHeader(sbt.aggregate(column.getTextColumnBuilder(), Calculation.SUM).setLabel("Amount Due").setLabelPosition(Position.LEFT).setLabelWidth(200).setLabelStyle(Templates.boldStyle).setStyle(Templates.boldStyle));
            }
            table.addColumn(column.getTextColumnBuilder());
//            column.getTextColumnBuilder().setWidth(column.getWidth());
        }

        table.setDataSource(dataSource);

        return table;

    }

}
