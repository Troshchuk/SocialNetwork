package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.User;

import java.util.List;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  18.07.14.
 */
public class UsersList {
    private int beginId;
    private UserDao userDao;

    public UsersList() {
        beginId = 0;
        userDao = new UserDaoImpl();
    }

    public List<User> getUserList() {
        try {
            List<User> users = userDao.selectNext(beginId);
            beginId += users.size();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
