package mum.cs490.tbs.report;

public class ChartColumn extends Column {

    private String color;

    private boolean data;

    private boolean label;

    public String getColor() {
        return color;
    }

    public boolean isData() {
        return data;
    }

    public boolean isLabel() {
        return label;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public void setLabel(boolean label) {
        this.label = label;
    }

}
