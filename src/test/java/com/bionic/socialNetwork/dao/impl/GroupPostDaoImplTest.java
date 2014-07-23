package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.GroupDao;

import com.bionic.socialNetwork.dao.GroupPostDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.models.GroupPost;

import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
    private GroupPostDao groupPostDao;

    private UserDao userDao;
    private User user;

    private Group group;
    private GroupDao groupDao;


    @Before
    public void beforeTest() throws Exception {

        user = new User("PostUser", "", "", "");
        userDao = new UserDaoImpl();
        userDao.insert(user, new Password("password"));

        group = new Group("First");
        groupDao = new GroupDaoImpl();
        groupDao.insert(group);

        groupPostDao = new GroupPostDaoImpl();
        groupPostActual = new GroupPost(group, user, "1 Post", new Date(
                new java.util.Date().getTime()));

        groupPostDao.insert(groupPostActual);
        id = groupPostActual.getGroupPostId();
    }

    @Test
    public void testSelectGroupPostById() throws Exception {
        GroupPost groupPostExpected = groupPostDao.selectById(id);
        assertEquals(groupPostExpected.getGroupPostId(),
                     groupPostActual.getGroupPostId());
    }

    @Test
    public void testSelectLastWith() throws Exception {
        List<GroupPost> groupPosts = new ArrayList<GroupPost>();

        for (int i = 0; i < 9 ; i++) {
            groupPosts.add(new GroupPost(group, user, "selectLast", new Date(
                    new java.util.Date().getTime())));
            groupPostDao.insert(groupPosts.get(i));
        }

        GroupPost groupPost = new GroupPost(group, user, "fsdf", new Date(
                new java.util.Date().getTime()));

        groupPostDao.insert(groupPost);
        List<GroupPost> result = groupPostDao.selectLastWith(group, 1);

        assertEquals(result.get(0).getGroupPostId(), groupPost.getGroupPostId());

        for (int i = 0; i < 9 ; i++) {
            groupPostDao.delete(groupPosts.get(i));
        }
        groupPostDao.delete(groupPost);
    }

    @After
    public void afterTest() throws Exception {
        groupPostDao.delete(groupPostActual);
        groupDao.delete(group);
        userDao.delete(user);
    }
}
