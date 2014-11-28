/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author gyanu
 */
@Entity
public class CallingCodes implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    private String country;
    @Id
    private int code;

    @Override
    public String toString() {
        return "CallingCodes{" + "country=" + country + ", code=" + code + '}';
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CallingCodes(String country, int code) {
        this.country = country;
        this.code = code;
    }

    public CallingCodes() {
    }

}
