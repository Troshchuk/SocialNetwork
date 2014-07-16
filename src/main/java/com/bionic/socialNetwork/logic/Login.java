package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.User;

/**
 * Created by Bish_ua on 16.07.2014.
 */
public class Login {
    private UserDaoImpl userDaoImpl = new UserDaoImpl();
    private User user;


    public User getUser(String login, String password) {
        try {
            user = userDaoImpl.getUserByLogin(login);
            if (user.getPassword().equals(password)) {
                return user;
            }
        } catch (Exception e) {
            //do nothing
        }
        return null;
    }
}