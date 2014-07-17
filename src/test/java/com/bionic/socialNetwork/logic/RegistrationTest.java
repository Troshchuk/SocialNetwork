package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class RegistrationTest {

    User user = null;
    private String name = "loginza";

    @Before
    public void addUserTest(){
        Registration registration = new Registration();
        registration.addUser(name, "password","name","surname");


    }


    @Test
    public void registerUserTest(){
        UserDaoImpl userDao = new UserDaoImpl();
        user = new User();
        try {
            user = userDao.selectByLogin(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            assertNotNull(userDao.selectByLogin(name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @After
    public void deleteCreatedUserTest(){
        UserDaoImpl userDao = new UserDaoImpl();
        try {
            userDao.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            assertNull(userDao.selectByLogin(name));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}