package mum.cs490.tbs.report;

import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;

import static net.sf.dynamicreports.report.builder.DynamicReports.concatenatedReport;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;

public class ReportUtil {

    public ReportUtil() {
    }

    public JasperConcatenatedReportBuilder concatenateReports(JasperReportBuilder[] builder) {
        return concatenatedReport().concatenate(builder);
    }

    public JasperReportBuilder getDynamicReport(Component component,
            ComponentBuilder<?, ?> componentBuilder) throws JRException {
        JasperReportBuilder report = report();
        report.summary(componentBuilder);
        return report;

    }

    public JasperReportBuilder getDynamicReport(ComponentBuilder<?, ?> componentBuilder,
            JRDataSource dataSource) {
        JasperReportBuilder report = report();
        report.setPageMargin(DynamicReports.margin());
        report.summary(componentBuilder);
        report.setDataSource(dataSource == null ? new JREmptyDataSource(0) : dataSource);
        return report;
    }

    public JasperReportBuilder getJasperReport() {
        JasperReportBuilder report = report();
        report.setDataSource(new JREmptyDataSource());
        return report;
    }

}
