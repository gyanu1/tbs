package mum.cs490.tbs.report;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;

public class Column {

    protected String key;

    private boolean lastColumn;

    protected String name;

    private TextColumnBuilder<?> textColumnBuilder;

    public Column() {

    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public TextColumnBuilder<?> getTextColumnBuilder() {
        return textColumnBuilder;
    }

    public boolean isLastColumn() {
        return lastColumn;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLastColumn(boolean lastColumn) {
        this.lastColumn = lastColumn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTextColumnBuilder(TextColumnBuilder<?> textColumnBuilder) {
        this.textColumnBuilder = textColumnBuilder;
    }

}
