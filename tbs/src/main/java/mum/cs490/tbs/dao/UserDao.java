/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.dao;

import java.util.List;
import mum.cs490.tbs.model.TbsUser;
import mum.cs490.tbs.model.UserRole;

/**
 *
 * @author gyanu
 */
public interface UserDao {

    public List<TbsUser> findUserByName(String name);

    public void saveUser(TbsUser user);

    public void saveUserRole(UserRole role);

}
