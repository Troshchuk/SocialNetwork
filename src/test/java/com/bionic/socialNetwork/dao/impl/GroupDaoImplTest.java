package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.GroupDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

/**
 * Test Group's DAO
 *
 * @author yoalex5
 * @version 1.0 17.07.14
 */
public class GroupDaoImplTest {
    private long id;
    private Group groupActual;
    private GroupDao groupDao;
    private User user;
    private UserDao userDao;

    @Before
    public void testInsertGroup() throws Exception {
        user = new User("GroupTest", "", "", "", new Date(0));
        userDao = new UserDaoImpl();
        userDao.insert(user, new Password("lol"));

        groupActual = new Group("First","Description", user);
        groupDao = new GroupDaoImpl();
        groupDao.insert(groupActual);

        id = groupActual.getGroupId();
    }

    @Test
    public void testSelectGroupById() throws Exception {
        Group groupExpected = groupDao.selectById(id);
        assertEquals(groupExpected.getGroupId(), groupActual.getGroupId());
    }

    @Test
    public void testUpdateGroup() throws Exception {
        groupActual.setName("Updated");
        groupDao.update(groupActual);
        Group groupExpected = groupDao.selectById(id);
        assertEquals(groupExpected.getGroupId(), groupActual.getGroupId());
    }

    @After
    public void testDeleteGroup() throws Exception {
        groupDao.delete(groupActual);
        userDao.delete(user);
    }
}
