/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.model;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author ARijal
 */

@Entity
public class CallingRate {
    
    @EmbeddedId
    private RateId id;
    private double peak;
    private double offPeak;
    

    public CallingRate() {
    }


    public RateId getId() {
        return id;
    }

    public void setId(RateId id) {
        this.id = id;
    }

    public double getPeak() {
        return peak;
    }

    public void setPeak(double peak) {
        this.peak = peak;
    }

    public double getOffPeak() {
        return offPeak;
    }

    public void setOffPeak(double offPeak) {
        this.offPeak = offPeak;
    }

   
    
    
    
}
