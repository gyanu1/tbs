/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mum.cs490.tbs.report;

import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author puneetkhanal
 */
public class BillRecord {
    
    private Long fromTelephoneNo;
    private BigInteger toTelephoneNo;
    private String fromCountry;
    private String destinationCountry;
    private Double duration;
    private Date callTime;
    private Double amount;
    private String service;

   

    public BillRecord() {

    }

    public Long getFromTelephoneNo() {
        return fromTelephoneNo;
    }

    public void setFromTelephoneNo(Long fromTelephoneNo) {
        this.fromTelephoneNo = fromTelephoneNo;
    }


    public String getFromCountry() {
        return fromCountry;
    }

    public void setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public BigInteger getToTelephoneNo() {
        return toTelephoneNo;
    }

    public void setToTelephoneNo(BigInteger toTelephoneNo) {
        this.toTelephoneNo = toTelephoneNo;
    }
    
    
    
    
}
