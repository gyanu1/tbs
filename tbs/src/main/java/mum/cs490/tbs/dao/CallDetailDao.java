/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.dao;

import java.util.List;
import mum.cs490.tbs.model.CallDetail;

/**
 *
 * @author ARijal
 */
public interface CallDetailDao {
    public void create(CallDetail callDetail);
    public void update(CallDetail callDetail);
    public List<CallDetail> get(Long telephoneNumber);
    
}
