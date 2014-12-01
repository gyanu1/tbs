package mum.cs490.tbs.report;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.jasperreports.engine.JRDataSource;
import org.apache.log4j.Logger;

import java.util.Collection;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

@SuppressWarnings("unchecked")
public class DynamicComponentBuilder {

    private Logger logger = Logger.getLogger(DynamicComponentBuilder.class.getName());

    public DynamicComponentBuilder() {
    }

    public JasperReportBuilder createHeader(Header header) {
        JasperReportBuilder headerReport = report();
        headerReport.setPageMargin(DynamicReports.margin());
        headerReport.fields(field("content", String.class));
//		TextFieldBuilder<String> textField = cmp.text(new TextExpression());
//		textField.setStyle(header.getHeaderStyle().getHeaderStyle(header));
//		headerReport.summary(textField);
        return headerReport;
    }

    public JasperReportBuilder createParagraph(Paragraph paragraph) throws ComponentException {
        JasperReportBuilder paragraphReport = report();
        paragraphReport.setPageMargin(DynamicReports.margin());
        paragraphReport.fields(field("content", String.class));
        StyleBuilder paragraphStyle = stl.style().setFontName(paragraph.getFontName())
                //				.setForegroudColor(Color.decode(paragraph.getColor()))
                .setFontSize(paragraph.getFontSize())
                .setHorizontalAlignment(HorizontalAlignment.JUSTIFIED).setBottomPadding(7)
                .setRightPadding(10);
//		TextFieldBuilder<String> textField = cmp.text(new TextExpression());
//		textField.setStyle(paragraphStyle);
        // textField.setStyle(stl.style().setHorizontalAlignment(HorizontalAlignment.JUSTIFIED)
        // .setMarkup(Markup.HTML));
//		paragraphReport.summary(textField);
        return paragraphReport;
    }

    public JasperReportBuilder createTable(Component component, JRDataSource dataSource)
            throws ComponentException {

//		setColumns(component);
        Collection<TableColumn> columns = (Collection<TableColumn>) component.getColumns();
        JasperReportBuilder table;
        table = report();
        table.setPageMargin(DynamicReports.margin(20));

        table.setPageColumnsPerPage(2);

        table.setPageColumnSpace(10);
        table.setTemplate(Templates.reportTemplate);

        for (TableColumn column : columns) {

            table.addColumn(column.getTextColumnBuilder());
//            column.getTextColumnBuilder().setWidth(column.getWidth());
        }

        table.setDataSource(dataSource);

        return table;

    }
    
    
    public JasperReportBuilder createCustomerBillTable(Component component, JRDataSource dataSource)
            throws ComponentException {

//		setColumns(component);
        Collection<TableColumn> columns = (Collection<TableColumn>) component.getColumns();
        JasperReportBuilder table;
        table = report();
        table.setPageMargin(DynamicReports.margin(20));

        table.setTemplate(Templates.reportTemplate);

        for (TableColumn column : columns) {

            table.addColumn(column.getTextColumnBuilder());
//            column.getTextColumnBuilder().setWidth(column.getWidth());
        }

        table.setDataSource(dataSource);

        return table;

    }

}
