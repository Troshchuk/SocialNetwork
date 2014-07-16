package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.AdministratorDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.Administrator;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public void beginState() throws Exception {
        UserDao userDao = new UserDaoImpl();
        user = new User("AdminTest", "", "", "");
        userDao.insert(user);
        administratorDao = new AdministratorDaoImpl();
    }

    @Test
    public void testInsert() throws Exception {
        UserDao userDao = new UserDaoImpl();

        Administrator administrator = new Administrator(user.getId());
        administratorDao.insert(administrator);


    }

    @After
    public void testDelete() throws Exception {
        Administrator administrator = new Administrator(user.getId());
        administratorDao.delete(administrator);
    }
}