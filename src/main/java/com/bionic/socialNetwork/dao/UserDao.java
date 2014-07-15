package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.User;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  14.07.14.
 */
public interface UserDao {
    public void addUser(User user) throws Exception;
    public User getUserById(long id) throws Exception;
    public User getUserByLogin(String login) throws Exception;
}
