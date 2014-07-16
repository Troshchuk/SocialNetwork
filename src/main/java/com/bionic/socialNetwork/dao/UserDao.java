package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.User;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  14.07.14.
 */
public interface UserDao {
    public void insert(User user) throws Exception;
    public User selectById(long id) throws Exception;
    public User selectByLogin(String login) throws Exception;
}
