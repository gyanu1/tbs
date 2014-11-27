package mum.cs490.tbs.report;

import net.sf.dynamicreports.report.constant.HorizontalAlignment;

public class Header extends Text {

    private HeaderStyles headerStyle;
    private HorizontalAlignment horizontalAlignment;

    public HeaderStyles getHeaderStyle() {
        return headerStyle;
    }

    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHeaderStyle(HeaderStyles headerStyle) {
        this.headerStyle = headerStyle;
    }

    public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

}
