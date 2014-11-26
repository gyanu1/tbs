/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ARijal
 */

@Entity
public class CallingRate {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private double peakRate;
    private double offPeakRate;
    @ManyToOne
    private CallingCodes destinationCountry;  
    @ManyToOne
    private Rateinfo rateinfo;

    public CallingRate() {
    }

    public CallingRate(double peakRate, CallingCodes destinationCountry, Rateinfo rateinfo) {
        this.peakRate = peakRate;
        this.destinationCountry = destinationCountry;
        this.rateinfo = rateinfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPeakRate() {
        return peakRate;
    }

    public void setPeakRate(double peakRate) {
        this.peakRate = peakRate;
    }

    public double getOffPeakRate() {
        return offPeakRate;
    }

    public void setOffPeakRate(double offPeakRate) {
        this.offPeakRate = offPeakRate;
    }

    public CallingCodes getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(CallingCodes destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    @Override
    public String toString() {
        return "CallingRate{" + "id=" + id + ", peakRate=" + peakRate + ", offPeakRate=" + offPeakRate + ", destinationCountry=" + destinationCountry + '}';
    }
    
    
    
    
    
    
    
}
