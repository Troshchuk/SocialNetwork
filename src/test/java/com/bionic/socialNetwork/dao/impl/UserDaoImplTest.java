package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
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
        User user = new User("userDaoTest", "Admin", "Admin", "Administrator",
                             new Date(0));

        userDao.insert(user, new Password("koks"));
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
        List<User> users = userDao.selectNext(0);
        assertNotNull(users);
    }

    @Test
    public void testSelectByName() throws Exception {
        List<User> users = userDao.selectByFullName("Admin", "Admin", 0);
        assertNotNull(users);
    }

    @Test
    public void testSelectFollowingsNext() throws Exception {
        User user = new User("user", "user", "user", "user", new Date(0));
        User friend1 = new User("temp", "temp", "temp", "temp", new Date(0));
        User friend2 = new User("temp", "temp", "temp", "temp", new Date(0));

        userDao.insert(user, new Password("1"));
        userDao.insert(friend1, new Password("1"));
        userDao.insert(friend2, new Password("1"));

        userDao.insertFollowing(user, friend1);
        userDao.insertFollowing(user, friend2);
        List<User> users = userDao.selectFollowingsNext(user.getId(), 0);
        assertEquals(users.size(), 2);

        userDao.deleteFollowing(user, friend1);
        userDao.deleteFollowing(user, friend2);
        userDao.delete(user);
        userDao.delete(friend1);
        userDao.delete(friend2);
    }

    @Test
    public void testSelectFollowingsByName() throws Exception {
        User user = new User("user", "user", "user", "user", new Date(0));
        User friend1 = new User("temp", "temp", "temp", "temp", new Date(0));
        User friend2 = new User("temp", "temp", "temp", "temp", new Date(0));

        userDao.insert(user, new Password("1"));
        userDao.insert(friend1, new Password("1"));
        userDao.insert(friend2, new Password("1"));

        userDao.insertFollowing(user, friend1);
        userDao.insertFollowing(user, friend2);
        List<User> users =
                userDao.selectFollowingsByFullName("temp", "temp", user.getId(),
                                                   0);
        assertEquals(users.size(), 2);

        userDao.deleteFollowing(user, friend1);
        userDao.deleteFollowing(user, friend2);
        userDao.delete(user);
        userDao.delete(friend1);
        userDao.delete(friend2);
    }

    @After
    public void testDelete() throws Exception {
        User user = userDao.selectById(id);
        userDao.delete(user);
    }
}