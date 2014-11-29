/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author ARijal
 */
@Entity
public class PeakInfo implements Serializable {


    private Date peakStart;
    private Date offPeakStart;

    @EmbeddedId
    private PeakId peakId;

    public PeakInfo() {
    }

    public Date getPeakStart() {
        return peakStart;
    }

    public void setPeakStart(Date peakStart) {
        this.peakStart = peakStart;
    }

    public Date getOffPeakStart() {
        return offPeakStart;
    }

    public void setOffPeakStart(Date offPeakStart) {
        this.offPeakStart = offPeakStart;
    }

    public PeakId getPeakId() {
        return peakId;
    }

    public void setPeakId(PeakId peakId) {
        this.peakId = peakId;
    }


}
