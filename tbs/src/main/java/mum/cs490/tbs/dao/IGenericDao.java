/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.dao;

import java.util.List;

/**
 *
 * @author Ajay
 */
public interface IGenericDao<T> {
    public void store(T t);
    public void update(T t);
    public List<T> getAll();
    
}
