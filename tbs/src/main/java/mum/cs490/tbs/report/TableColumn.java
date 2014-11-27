package mum.cs490.tbs.report;

import java.math.BigDecimal;

public class TableColumn extends Column {

    private BigDecimal percentageWidth;
    private int width;

    public TableColumn() {

    }

    public BigDecimal getPercentageWidth() {
        return percentageWidth;
    }

    public int getWidth() {
        return width;
    }

    public void setPercentageWidth(BigDecimal percentageWidth) {
        this.percentageWidth = percentageWidth;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "TableColumn [percentageWidth=" + percentageWidth + ", width=" + width + ", key="
                + key + ", name=" + name + "]";
    }
}
