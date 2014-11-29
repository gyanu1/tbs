/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ARijal
 */

@Entity
public class CallDetail implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private double duration;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date callDate;
     @Temporal(javax.persistence.TemporalType.TIME)
    private Date callTime;
    @ManyToOne
    private CallingCodes fromCountry;
    @ManyToOne
    private CallingCodes toCountry;
    @ManyToOne
    private Customer fromCustomer;
    @ManyToOne
    private Customer toCustomer;

    public CallDetail() {
    }

    
    public CallDetail(double duration, Date callDate, Date callTime, CallingCodes fromCountry, CallingCodes toCountry, Customer fromCustomer, Customer toCustomer) {
        this.duration = duration;
        this.callDate = callDate;
        this.callTime = callTime;
        this.fromCountry = fromCountry;
        this.toCountry = toCountry;
        this.fromCustomer = fromCustomer;
        this.toCustomer = toCustomer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }


    public CallingCodes getFromCountry() {
        return fromCountry;
    }

    public void setFromCountry(CallingCodes fromCountry) {
        this.fromCountry = fromCountry;
    }

    public CallingCodes getToCountry() {
        return toCountry;
    }

    public void setToCountry(CallingCodes toCountry) {
        this.toCountry = toCountry;
    }

    public Customer getFromCustomer() {
        return fromCustomer;
    }

    public void setFromCustomer(Customer fromCustomer) {
        this.fromCustomer = fromCustomer;
    }

    public Customer getToCustomer() {
        return toCustomer;
    }

    public void setToCustomer(Customer toCustomer) {
        this.toCustomer = toCustomer;
    }

    @Override
    public String toString() {
        return "CallDetail{" + "id=" + id + ", duration=" + duration + ", callDate=" + callDate + ", callTime=" + callTime + ", fromCountry=" + fromCountry + ", toCountry=" + toCountry + ", fromCustomer=" + fromCustomer + ", toCustomer=" + toCustomer + '}';
    }
    
    

    
    
    
    
    
}
