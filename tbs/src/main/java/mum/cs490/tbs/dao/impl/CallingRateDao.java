/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.dao.impl;

import java.util.List;
import mum.cs490.tbs.model.CallingRate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gyanu
 */
@Repository
public class CallingRateDao extends GenericDaoII<CallingRate> {
    public List<CallingRate> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from CallingRate").list();
    }

}
