/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.model;

import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ARijal
 */

@Entity
public class PeakInfo {
    @Id
    @GeneratedValue
    private Long id;
    private Time peakStart;
    private Time offPeakStart;
    
//    @ManyToOne
//    private CallingCard callingCard;
    @ManyToOne
    private Service service;

    public PeakInfo() {
    }
    
    public PeakInfo(Time peakStart, Time offPeakStart) {
        this.peakStart = peakStart;
        this.offPeakStart = offPeakStart;
    }

    public Time getPeakStart() {
        return peakStart;
    }

    public void setPeakStart(Time peakStart) {
        this.peakStart = peakStart;
    }

    public Time getOffPeakStart() {
        return offPeakStart;
    }

    public void setOffPeakStart(Time offPeakStart) {
        this.offPeakStart = offPeakStart;
    }

//    public CallingCard getCallingCard() {
//        return callingCard;
//    }
//
//    public void setCallingCard(CallingCard callingCard) {
//        this.callingCard = callingCard;
//    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
