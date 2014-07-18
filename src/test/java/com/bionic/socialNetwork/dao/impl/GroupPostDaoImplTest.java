package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.GroupDAO;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.models.GroupPost;

import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;


/**
 * Test GroupPost's DAO
 *
 * @author yoalex5
 * @version 1.0 16.07.14
 */
public class GroupPostDaoImplTest {

    private long id;
    private GroupPost groupPostActual;
    private GroupDAO<GroupPost> groupPostDao;

    private UserDao userDao;
    private User user;

    private Group group;
    private GroupDAO<Group> groupDao;

    @Before
    public void preExecute() throws Exception {

        user = new User("PostUser", "", "", "");
        userDao = new UserDaoImpl();
        userDao.insert(user, new Password("password"));

        group = new Group("First");
        groupDao = new GroupDaoImpl();
        groupDao.insert(group);

        groupPostDao = new GroupPostDaoImpl();
        groupPostActual = new GroupPost(group.getGroupId(), user.getId(), "1 Post");

        testInsertGroupPost();
    }

    @Test
    public void testSelectGroupPostById() throws Exception {
        GroupPost groupPostExpected = groupPostDao.selectById(id);
        assertEquals(groupPostExpected, groupPostActual);
    }

    @After
    public void postExecute() throws Exception {

        groupPostDao.delete(groupPostActual);

        groupDao.delete(group);

        userDao.delete(user);
    }

    private void testInsertGroupPost() throws Exception {
        groupPostDao.insert(groupPostActual);
        id = groupPostActual.getGroupPostId();
    }

}
