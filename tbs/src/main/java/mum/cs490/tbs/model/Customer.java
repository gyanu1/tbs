/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ARijal
 */
@Entity
public class Customer implements Serializable {

    @Id
    private Long telephoneNumber;
    @ManyToOne
    private Service Service;
    private String firstname;
    private String lastname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private int commission;
    @ManyToOne
    private TbsUser salesRep;

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public TbsUser getSalesRep() {
        return salesRep;
    }

    public void setSalesRep(TbsUser salesRep) {
        this.salesRep = salesRep;
    }


    @Override
    public String toString() {
        return "mum.cs490.tbs.model.UserRole[ telephoneNumber=" + telephoneNumber + ",firstname=" + firstname + ",lastname=" + lastname + ",country=" + country + ",service=" + Service + " ]";
    }

}
