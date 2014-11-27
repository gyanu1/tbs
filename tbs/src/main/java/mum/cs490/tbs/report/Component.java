package mum.cs490.tbs.report;

import java.math.BigDecimal;
import java.util.Collection;

public class Component {

    private String data;

    private Collection<?> columns;
    private boolean empty;

    private String name;

    private String panelHeader = "";

    private BigDecimal percentageWidth;

    private String remoteQuery;

    private int rowCount;

    private ComponentType type;

    public Component() {

    }

    public Collection<?> getColumns() {
        return columns;
    }

    public void setColumns(Collection<?> columns) {
        this.columns = columns;
    }

    public String getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public String getPanelHeader() {
        return panelHeader;
    }

    public BigDecimal getPercentageWidth() {
        return percentageWidth;
    }

    public String getRemoteQuery() {
        return remoteQuery;
    }

    public int getRowCount() {
        return rowCount;
    }

    public ComponentType getType() {
        return type;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPanelHeader(String panelHeader) {
        this.panelHeader = panelHeader;
    }

    public void setPercentageWidth(BigDecimal percentageWidth) {
        this.percentageWidth = percentageWidth;
    }

    public void setRemoteQuery(String remoteQuery) {
        this.remoteQuery = remoteQuery;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

}
