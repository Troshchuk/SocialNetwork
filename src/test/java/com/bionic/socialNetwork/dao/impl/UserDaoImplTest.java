package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.User;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    @Test
    public void testAddUser() throws Exception {
        User user = new User("root", "Admin", "Admin", "Administrator");

        UserDao userDao = new UserDaoImpl();
        userDao.addUser(user);
    }

    @Test
    public void testGetUserById() throws Exception {
        long id = 1;
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserById(id);
        assertNotNull(user);
    }

    @Test
    public void testGetUserByLogin() throws Exception {
        String login = "root";
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByLogin(login);
        assertNotNull(user);
    }
}