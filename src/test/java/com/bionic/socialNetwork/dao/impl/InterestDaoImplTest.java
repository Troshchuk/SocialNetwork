package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.InterestDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.Interest;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


/**
 * Test for InterestsDao
 *
 * @author Matvii Mitnitskyi
 *         version 1.00  21.07.2014
 */

public class InterestDaoImplTest {
    private InterestDao interestDao;
    private UserDao userDao;
    private Interest interest;
    private String interestName;
    private long id, id2;
    private String login;

    @Before
    public void testInsert() throws Exception {
        interestDao = new InterestDaoImpl();
        userDao = new UserDaoImpl();
        interest = new Interest(1, "Unit testing in Java");
        interestDao.insert(interest);
        User user =
                new User("interestUserTest", "Admin", "Admin", "Administrator",
                         new Date(0));
        Password password = new Password("password");
        userDao.insert(user, password);
        interestName = interest.getInterest();
        login = user.getLogin();
        id = user.getId();
        id2 = interest.getInterests_id();
    }

    @Test
    public void selectById() throws Exception {
        Interest interest = interestDao.selectById(id2);
        assertEquals(interestName, interest.getInterest());
    }

    @Test
    public void selectByInterest() throws Exception {
        Interest interest1 =
                interestDao.selectByInterest(interest.getInterest());
        assertEquals(id2, interest1.getInterests_id());
    }

    @After
    public void testDelete() throws Exception {

        interestDao.delete(interest);
        userDao.delete(userDao.selectById(id));
        assertNull(interestDao.selectById(id2));
    }
}
