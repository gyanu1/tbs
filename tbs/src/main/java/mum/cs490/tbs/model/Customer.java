/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ARijal
 */

@Entity
public class Customer {
   
    @Id
    private Long telephoneNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    private Service Service;

    public Customer() {
    }

    public Customer(Long telephoneNumber, Service Service) {
        this.telephoneNumber = telephoneNumber;
        this.Service = Service;
    }

    public Long getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Long telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Service getService() {
        return Service;
    }

    public void setService(Service Service) {
        this.Service = Service;
    }
    
    
    
}
