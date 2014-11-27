/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.dao;

import mum.cs490.tbs.model.Customer;

/**
 *
 * @author Ajay
 */
public interface CustomerDao {
    public void create(Customer customer);
    public void update(Customer customer);
    
}
