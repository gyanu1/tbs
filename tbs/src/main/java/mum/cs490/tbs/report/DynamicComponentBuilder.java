package mum.cs490.tbs.report;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.jasperreports.engine.JRDataSource;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
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

    public ComponentBuilder<?, ?> createTable(Component component, JRDataSource dataSource)
            throws ComponentException {

//		setColumns(component);
        Collection<TableColumn> columns = (Collection<TableColumn>) component.getColumns();
        JasperReportBuilder table;
        table = report();
        table.setPageMargin(DynamicReports.margin());
        table.setTemplate(TableTemplate.reportTemplate);
        BigDecimal totalPercentageWidth = new BigDecimal(0);
        for (TableColumn column : columns) {
            if (column.getPercentageWidth() != null) {
                BigDecimal roundedWidth = column.getPercentageWidth().setScale(3,
                        BigDecimal.ROUND_DOWN);
                BigDecimal sum = totalPercentageWidth.add(roundedWidth);
                totalPercentageWidth = sum;
                if (sum.compareTo(new BigDecimal(1)) > 0) {
                    logger.warn(this.getClass().getSimpleName()
                            + ";create table;invlaid column widths for table component" + component);
//                    resetColumWidths(columns, component);
                    break;
                }
            }
        }
        for (TableColumn column : columns) {

            table.addColumn(column.getTextColumnBuilder());
        }

        table.setDataSource(dataSource);
        return cmp.subreport(table);

    }

}
