/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.model;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private CallingCodes destinationCountry;

    public CallingRate() {
    }

    public CallingRate(double peakRate, double offPeakRate, CallingCodes destinationCountry) {
        this.peakRate = peakRate;
        this.offPeakRate = offPeakRate;
        this.destinationCountry = destinationCountry;
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
