package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;

/**
 * Created by Bish_ua on 17.07.2014.
 */
public class Registration {


    public boolean addUser(String login, String password, String name, String surname){
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setLogin(login);
        user.setName(name);
        user.setSurname(surname);

        try {
            userDao.insert(user, new Password(password));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
}
