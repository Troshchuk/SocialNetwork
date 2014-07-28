package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.AdministratorDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.Administrator;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

/**
 * Test administrator's DAO
 *
 * @author Dmytro Troshchuk
 * @version 1.00  16.07.14.
 */
public class AdministratorDaoImplTest {
    private User user;
    private AdministratorDao administratorDao;

    @Before
    public void testInsert() throws Exception {
        UserDao userDao = new UserDaoImpl();
        user = new User("AdminTest", "", "", "", new Date(0));
        userDao.insert(user, new Password("password"));
        administratorDao = new AdministratorDaoImpl();

        Administrator administrator = new Administrator(user.getId());
        administratorDao.insert(administrator);
    }

    @Test
    public void testSelectAll() throws Exception {
        List<Administrator> administrators = administratorDao.selectAll();
        Administrator administrator = administratorDao.selectAll().get(0);
        assertNotNull(administrator);
    }

    @After
    public void testDelete() throws Exception {
        Administrator administrator = new Administrator(user.getId());
        administratorDao.delete(administrator);
        new UserDaoImpl().delete(user);
    }
}