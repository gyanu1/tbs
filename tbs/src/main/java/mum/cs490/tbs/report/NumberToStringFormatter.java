/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mum.cs490.tbs.report;

import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.definition.ReportParameters;

/**
 *
 * @author puneetkhanal
 */
public class NumberToStringFormatter extends AbstractValueFormatter<String, Number>{

    @Override
    public String format(Number value, ReportParameters reportParameters) {
        return value.longValue()+"";
    }
    
}
