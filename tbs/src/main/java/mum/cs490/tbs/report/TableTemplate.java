package mum.cs490.tbs.report;

import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;


import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.template;

public class TableTemplate {

	public static final StyleBuilder columnTitleStyle;

	public static final ReportTemplateBuilder reportTemplate;

	static {
		StyleBuilder baseStyle = stl.style().setTopBorder(stl.penThin()).setLeftBorder(stl.penThin())
				.setRightBorder(stl.penThin());
		columnTitleStyle = stl.style(baseStyle).setPadding(5)
				.setHorizontalAlignment(HorizontalAlignment.LEFT);

		reportTemplate = template().setColumnTitleStyle(columnTitleStyle);
	}

}
