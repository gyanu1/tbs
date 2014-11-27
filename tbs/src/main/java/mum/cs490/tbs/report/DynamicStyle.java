package mum.cs490.tbs.report;

import net.sf.dynamicreports.report.builder.style.StyleBuilder;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

public class DynamicStyle {

    public static StyleBuilder getBorderedColumnStyle(int fontSize, boolean lastColumn) {

        StyleBuilder baseStyle = stl.style()
                .setBottomBorder(stl.penThin());
        if (!lastColumn) {
            baseStyle.setRightBorder(stl.penThin());
        }
        StyleBuilder style = stl.style(baseStyle).setPadding(3).setFontSize(fontSize);

        return style;
    }

}
