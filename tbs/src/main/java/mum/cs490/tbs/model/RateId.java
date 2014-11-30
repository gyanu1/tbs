/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mum.cs490.tbs.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author puneetkhanal
 */
@Embeddable
public class RateId implements Serializable{
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updateDate;
    @ManyToOne
    private Service service;
    @ManyToOne
    private CallingCodes destinationCountry;
    private String sourceCountry;
    
    public RateId(){
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public CallingCodes getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(CallingCodes destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getSourceCountry() {
        return sourceCountry;
    }

    public void setSourceCountry(String sourceCountry) {
        this.sourceCountry = sourceCountry;
    }

    
    
   
}
