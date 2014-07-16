package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.User;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;

import static org.junit.Assert.*;

/**
 * Test user's DAO
 *
 * @author Dmytro Troshchuk
 * @version 1.00  16.07.14.
 */
public class UserDaoImplTest {

    @Test
    public void testInsert() throws Exception {
        User user = new User("root", "Admin", "Admin", "Administrator");

        UserDao userDao = new UserDaoImpl();
        userDao.insert(user);
    }

    @Test
    public void testSelectById() throws Exception {
        long id = 1;
        UserDao userDao = new UserDaoImpl();
        User user = userDao.selectById(id);
        assertNotNull(user);
    }

    @Test
    public void testSelectByLogin() throws Exception {
        String login = "root";
        UserDao userDao = new UserDaoImpl();
        User user = userDao.selectByLogin(login);
        assertNotNull(user);
    }
}