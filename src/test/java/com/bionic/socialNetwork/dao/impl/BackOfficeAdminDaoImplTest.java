package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.BackOfficeAdminDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.BackOfficeAdmin;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BackOfficeAdminDaoImplTest {

    private User user;
    private BackOfficeAdminDao backOfficeAdminDao;

    @Before
    public void testInsert() throws Exception {
        UserDao userDao = new UserDaoImpl();
        user = new User("BackAdminTest", "", "", "", new Date(0));
        userDao.insert(user, new Password("password"));
        backOfficeAdminDao = new BackOfficeAdminDaoImpl();

        BackOfficeAdmin backOfficeAdmin = new BackOfficeAdmin(user.getId());
        backOfficeAdminDao.insert(backOfficeAdmin);


    }

    @Test
    public void testSelectAll() throws Exception {
        List<BackOfficeAdmin> backOfficeAdmins = backOfficeAdminDao.selectAll();
        BackOfficeAdmin backOfficeAdmin = backOfficeAdminDao.selectAll().get(0);
        assertNotNull(backOfficeAdmin);
    }

    @After
    public void testDelete() throws Exception {
        BackOfficeAdmin backOfficeAdmin = new BackOfficeAdmin(user.getId());
        backOfficeAdminDao.delete(backOfficeAdmin);
        new UserDaoImpl().delete(user);
    }
}