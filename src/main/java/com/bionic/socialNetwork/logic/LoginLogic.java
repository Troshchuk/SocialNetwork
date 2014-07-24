package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.User;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by Bish_ua on 16.07.2014.
 */
public class LoginLogic {
    private UserDaoImpl userDaoImpl = new UserDaoImpl();
    private User user;


    public User getUser(String login, String password) {

        try {
            user = userDaoImpl.selectByLogin(login);

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes(), 0, password.length());
            String md5 = new BigInteger(1, md.digest()).toString(16);

            if(user !=null && md5.equals(user.getPassword().getPassword())) {
                return user;
            }else {
                return null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}