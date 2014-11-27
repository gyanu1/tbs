/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.dao;

import java.util.List;
import mum.cs490.tbs.model.User;

/**
 *
 * @author gyanu
 */
public interface UserDao {

    public List<User> findUserByName(String name);

    public User SaveUser(User user);

}
