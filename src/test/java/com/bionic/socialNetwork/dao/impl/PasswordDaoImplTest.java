package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PasswordDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordDaoImplTest {
    private PasswordDao passwordDao;
    private long id;

    @Before
    public void beginState() throws Exception {
        User user = new User("PasswordTest", "PasswordTest", "PasswordTest", "");
        UserDao userDao = new UserDaoImpl();
        userDao.addUser(user);
        id = user.getId();
        passwordDao = new PasswordDaoImpl();
        passwordDao.addPasswordById(id, "Password");
    }

    @Test
    public void testGetPasswordById() throws Exception {
        String password = passwordDao.getPasswordById(id);
        assertEquals(password, "Password");
    }

    @Test
    public void testSetPasswordById() throws Exception {
        String password = passwordDao.getPasswordById(id);
        String newPassword = "NewPassword";
        assertNotEquals(password, newPassword);

        passwordDao.updatePasswordById(id, newPassword);
        password = passwordDao.getPasswordById(id);
        assertEquals(password, newPassword);
    }
}