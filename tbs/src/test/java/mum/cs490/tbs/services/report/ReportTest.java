package mum.cs490.tbs.services.report;


import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

import mum.cs490.tbs.report.ChartColumn;
import mum.cs490.tbs.report.Component;
import mum.cs490.tbs.report.ComponentException;
import mum.cs490.tbs.report.DataSource;
import mum.cs490.tbs.report.DynamicComponentBuilder;
import mum.cs490.tbs.report.ExporterService;
import mum.cs490.tbs.report.ReportService;
import mum.cs490.tbs.report.TableColumn;
import mum.cs490.tbs.report.TemplateProvider;
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

public class ReportTest {

	private static Collection<ChartColumn> chartColumns;

	private static Collection<TableColumn> columns;

	private static JRDataSource dataSource;

        private DynamicComponentBuilder componentBuilder=new DynamicComponentBuilder();
        private ReportService reportService=new ReportService();
        private ExporterService exporterService=new ExporterService();
	public static String testOutputPath = "components/";

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
	public void createTable() throws ComponentException, FileNotFoundException, DRException {
		Component component = new Component();
		component.setColumns(columns);
		JasperReportBuilder table = reportService.getDynamicReport(
				componentBuilder.createTable(component, dataSource), new JREmptyDataSource());
		table.setTemplateDesign(TemplateProvider.getTemplate("report_layout/defaulttemplate.jrxml"));
		exporterService.exportToPdf(table, testOutputPath, "Table");
		Assert.assertTrue(FileUtil.fileExists(testOutputPath + "Table.pdf"));
	}

	
	@Before
	public void setup() {
		TextColumnBuilder<String> userColumn = col.column("Name", "Name", type.stringType());
		TextColumnBuilder<Integer> rollColumn = col.column("Roll", "Roll", type.integerType());
		TextColumnBuilder<Integer> marksColumn = col.column("Marks", "Marks", type.integerType());
		TextColumnBuilder<String> userColumn1 = col.column("Name", "Name", type.stringType());
		TextColumnBuilder<Integer> rollColumn1 = col.column("Roll", "Roll", type.integerType());
		TextColumnBuilder<Integer> marksColumn1 = col.column("Marks", "Marks", type.integerType());
		chartColumns = new ArrayList<ChartColumn>();
		chartColumns.add(createChartColumn("Name", userColumn, false, true));
		chartColumns.add(createChartColumn("Roll", rollColumn, true, false));
		chartColumns.add(createChartColumn("Marks", marksColumn, true, false));
		columns = new ArrayList<TableColumn>();
		columns.add(createColumn("Name", userColumn1, true, false));
		columns.add(createColumn("Roll", rollColumn1, false, false));
		columns.add(createColumn("Marks", marksColumn1, false, true));
		dataSource = createDataSource();
	}
}
