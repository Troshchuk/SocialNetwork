package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Test user's DAO
 *
 * @author Dmytro Troshchuk
 * @version 1.00  16.07.14.
 */
public class UserDaoImplTest {
    private UserDao userDao;
    private long id;
    private String login;

    @Before
    public void testInsert() throws Exception {
        userDao = new UserDaoImpl();
        User user = new User("root2", "Admin", "Admin", "Administrator");
        userDao.insert(user);
        id = user.getId();
        login = user.getLogin();
    }

    @Test
    public void testSelectById() throws Exception {
        User user = userDao.selectById(id);
        assertEquals(login, user.getLogin());
    }

    @Test
    public void testSelectByLogin() throws Exception {
        User user = userDao.selectByLogin(login);
        assertEquals(id, user.getId());
    }

    @Test
    public void testSelectNext() throws Exception {
        List<User> users = userDao.selectNext(id);
        assertEquals(id, users.get(0).getId());
    }

    @After
    public void testDelete() throws Exception {
        User user = userDao.selectById(id);
        userDao.delete(user);
    }
}