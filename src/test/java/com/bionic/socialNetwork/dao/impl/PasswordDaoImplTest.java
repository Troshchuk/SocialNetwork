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
    private String pass = "Password";
    private long id;

    @Before
    public void beginState() throws Exception {
        User user = new User("PasswordTest", "PasswordTest", "PasswordTest", "");
        UserDao userDao = new UserDaoImpl();
        userDao.insert(user);
        id = user.getId();
        passwordDao = new PasswordDaoImpl();
        Password password = new Password(user.getId(), pass);
        passwordDao.insert(password);
    }

    @Test
    public void testGetPasswordById() throws Exception {
        Password password = passwordDao.selectById(id);
        assertEquals(password.getPassword(), pass);
    }

    @Test
    public void testSetPasswordById() throws Exception {
        Password password = passwordDao.selectById(id);
        String newPassword = "NewPassword";
        assertNotEquals(password.getPassword(), newPassword);

        password.setPassword(newPassword);
        passwordDao.update(password);
        password = passwordDao.selectById(id);
        assertEquals(password.getPassword(), newPassword);
    }
}