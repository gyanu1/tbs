package mum.cs490.tbs.report;

import java.awt.Color;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;


import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.template;

public class TableTemplate {

	public static final StyleBuilder columnTitleStyle;

	public static final ReportTemplateBuilder reportTemplate;

	static {
		StyleBuilder baseStyle = stl.style().setBorder(stl.penThin());
		columnTitleStyle = stl.style(baseStyle).setPadding(5).setForegroundColor(Color.BLACK)
				.setHorizontalAlignment(HorizontalAlignment.LEFT);

		reportTemplate = template().setColumnTitleStyle(columnTitleStyle).setColumnStyle(columnTitleStyle);
                
	}

}
