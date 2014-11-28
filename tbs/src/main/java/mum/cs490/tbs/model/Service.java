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
 * @author ARijal
 */
@Entity
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String serviceName;

    public Service() {
    }

    public Service(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    
}
