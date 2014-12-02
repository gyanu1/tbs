/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mum.cs490.tbs.services;

/**
 *
 * @author puneetkhanal
 */
public class SalesRep {
    
    private int id;
    private int commission;
    private Long telephoneNo;

    public Long getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(Long telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

  

    public SalesRep() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }
    
    
}
