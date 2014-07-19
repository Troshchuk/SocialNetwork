package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.SessionUserDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.SessionUserDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.SessionUser;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class SessionControllerTest {

//    User user;
//    UserDao userDao;
//    SessionUserDao sessionUserDao;
//    SessionUser sessionUser;
//    @Before
//    public void create() throws Exception {
//        user = new User("loginForTest","","","");
//        userDao = new UserDaoImpl();
//        Password password = new Password("password");
//        userDao.insert(user, password);
//    }
//
//
//    @Test
//    public void testSessionController() throws Exception {
//
//        SessionController sessionController = new SessionController();
//        sessionUser = sessionController.getNewSession(user);
//        assertNotNull(sessionUser);
//
//
//
//
//
//
//    }
//    @After
//    public void delete() throws Exception {
//
//        sessionUserDao = new SessionUserDaoImpl();
//        sessionUserDao.delete(sessionUser);
//        userDao.delete(user);
//        assertNull(userDao.selectByLogin("loginForTest"));
//
//    }


}