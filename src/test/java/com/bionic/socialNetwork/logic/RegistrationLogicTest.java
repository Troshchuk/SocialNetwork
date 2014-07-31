package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.InviteDao;
import com.bionic.socialNetwork.dao.impl.InviteDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Invite;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class RegistrationLogicTest {

    User user;
    Invite invite;
    String login = "gmail@gmail.com";


    @Before
    public void addUserTest() throws Exception {
        RegistrationLogic registrationLogic = new RegistrationLogic();
        InviteDao inviteDao = new InviteDaoImpl();
        invite = new Invite("123");
        inviteDao.insert(new Invite("123"));
        registrationLogic.register("name", "surname", login, "Password1", "123");
    }


   @Test
       public void registerUserTest() throws Exception {
           UserDaoImpl userDao = new UserDaoImpl();
           user = new User();
           user = userDao.selectByLogin(login);
           assertNotNull(userDao.selectByLogin(login));

    }
    @After
    public void deleteCreatedUserTest() throws Exception {
        InviteDao inviteDao = new InviteDaoImpl();
        inviteDao.delete(invite);
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.delete(user);
        assertNull(userDao.selectByLogin(login));
    }

}