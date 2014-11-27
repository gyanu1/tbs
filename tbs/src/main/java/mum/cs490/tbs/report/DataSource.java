package mum.cs490.tbs.report;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.*;

public class DataSource implements JRDataSource {

    private String[] columns;
    private Map<String, Object> currentRecord;
    private Iterator<Map<String, Object>> iterator;
    private List<Map<String, Object>> values;

    public DataSource(String... columns) {
        this.columns = columns;
        this.values = new ArrayList<Map<String, Object>>();
    }

    public void add(Object... values) {
        Map<String, Object> row = new HashMap<String, Object>();
        for (int i = 0; i < values.length; i++) {
            row.put(columns[i], values[i]);
        }
        this.values.add(row);
    }

    @Override
    public Object getFieldValue(JRField field) throws JRException {
        return currentRecord.get(field.getName());
    }

    @Override
    public boolean next() throws JRException {
        if (iterator == null) {
            this.iterator = values.iterator();
        }
        boolean hasNext = iterator.hasNext();
        if (hasNext) {
            currentRecord = iterator.next();
        }
        return hasNext;
    }
}
