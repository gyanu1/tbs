/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import org.apache.poi.hssf.record.CountryRecord;

/**
 *
 * @author ARijal
 */

@Entity
public class CallingRateList {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updateDate;
    
    @OneToMany
    @JoinColumn
    private List<CallingRate> destinationRateList;
    
    @ManyToOne
    private Service service;
    @ManyToOne
    private CallingCodes sourceCountry;

    
    
    public CallingRateList() {
    }


    public CallingRateList(Date updateDate, Service service, CallingCodes sourceCountry) {
        
        this.updateDate = updateDate;
        this.service = service;
        this.sourceCountry = sourceCountry;
    }
    
    public void addCallingRate(CallingRate callingRate){
        if (this.destinationRateList ==null)
            this.destinationRateList = new ArrayList<>();
        this.destinationRateList.add(callingRate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<CallingRate> getDestinationRateList() {
        return destinationRateList;
    }

    public void setDestinationRateList(List<CallingRate> destinationRateList) {
        this.destinationRateList = destinationRateList;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public CallingCodes getSourceCountry() {
        return sourceCountry;
    }

    public void setSourceCountry(CallingCodes sourceCountry) {
        this.sourceCountry = sourceCountry;
    }
    
    
    
    
    
    
    
}
