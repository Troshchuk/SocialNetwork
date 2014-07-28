package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.SessionUserDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.SessionUser;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author Denis
 * @version 1.00  18.07.2014.
 */
public class SessionUserDaoImplTest {
    private User user;
    private SessionUserDao sessionUserDao;
    private long sessionId;

    @Before
    public void testInsert() throws Exception {
        UserDao userDao = new UserDaoImpl();
        user = new User("SessionUser", "", "", "", new Date(0));
        userDao.insert(user, new Password("root"));

        sessionUserDao = new SessionUserDaoImpl();
        SessionUser sessionUser = new SessionUser("MySession", user);
        sessionUserDao.insert(sessionUser);

        sessionId = sessionUser.getSessionId();
        assertEquals(sessionUser.getSessionId(),
                     sessionUserDao.selectById(sessionId).getSessionId());
    }

    @Test
    public void testSelectById() throws Exception {
        SessionUser sessionUser = sessionUserDao.selectById(sessionId);
        assertNotNull(sessionUser);
    }

    @Test
    public void testSelectBySession() throws Exception {
        SessionUser sessionUser = sessionUserDao.selectBySession("MySession");
        assertNotNull(sessionUser);
    }

    @After
    public void testDelete() throws Exception {
        sessionUserDao.delete(sessionUserDao.selectById(sessionId));
        new UserDaoImpl().delete(user);
        assertNull(sessionUserDao.selectById(sessionId));
    }
}
