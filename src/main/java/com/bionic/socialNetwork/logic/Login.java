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
            user = userDaoImpl.selectByLogin(login);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(user !=null && password.equals(user.getPassword().getPassword())) {
            return user;
        }else {
            return null;
        }

    }
}