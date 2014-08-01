package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.BackOfficeAdminDao;
import com.bionic.socialNetwork.dao.PostDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.BackOfficeAdmin;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.Post;
import com.bionic.socialNetwork.models.User;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Denis, Dmytro Troshchuk
 * @version 1.00  16.07.2014.
 */
public class PostDaoImplTest {
    private User user;
    private PostDao postDao;
    private long postId;
    //    private Post post;
    private BackOfficeAdminDao backOfficeAdminDao;
    private BackOfficeAdmin backOfficeAdmin;

    @Before
    public void testInsert() throws Exception {
        UserDao userDao = new UserDaoImpl();
        user = new User("PostUser", "", "", "", new java.sql.Date(0));
        userDao.insert(user, new Password("password"));

        postDao = new PostDaoImpl();
        Post post = new Post("Some Post", user,
                             new Timestamp(new Date().getTime()));
        postDao.insert(post);

        backOfficeAdmin = new BackOfficeAdmin(user.getId());
        backOfficeAdminDao = new BackOfficeAdminDaoImpl();
        backOfficeAdminDao.insert(backOfficeAdmin);

        postId = post.getPostId();
        assertEquals(post.getPostId(), postDao.selectById(postId).getPostId());
    }

    @Test
    public void testSelectById() throws Exception {
        Post post = postDao.selectById(postId);
        assertNotNull(post);
    }

    @Test
    public void testSelectLastWith() throws Exception {
        postDao = new PostDaoImpl();
        List<Post> posts = new ArrayList<Post>();

        Post post = new Post("Test", user, new Timestamp(
                new Date().getTime()));


        Thread.sleep(1000);

        for (int i = 0; i < 9; i++) {
            posts.add(new Post("TestSelectWith", user, new Timestamp(
                    new Date().getTime())));
            postDao.insert(posts.get(i));

        }

        postDao.insert(post);
        List<Post> result = postDao.selectLastWith(user, 1);
        assertEquals(result.get(0).getPostId(),
                     post.getPostId());

        for (int i = 0; i < 9; i++) {
            postDao.delete(posts.get(i));
        }
        postDao.delete(post);
    }

    @Test
    public void testSelectBackOffLastWith() throws Exception {
        postDao = new PostDaoImpl();

        List<Post> posts = new ArrayList<Post>();
        Post post = new Post("Test", user, new Timestamp(
                new Date().getTime()));

        Thread.sleep(1000);

        for (int i = 0; i < 9; i++) {
            posts.add(new Post("TestSelectWith", user,
                               new Timestamp(new Date().getTime())));
            postDao.insert(posts.get(i));
        }

        postDao.insert(post);
        List<Post> result =
                postDao.selectLastBeckOffWith(backOfficeAdminDao.selectAll(),
                                              1);
        assertEquals(result.get(0).getPostId(), post.getPostId());

        for (int i = 0; i < 9; i++) {
            postDao.delete(posts.get(i));
        }
        postDao.delete(post);
    }

    @After
    public void testDelete() throws Exception {
        postDao.delete(postDao.selectById(postId));
        backOfficeAdminDao.delete(backOfficeAdmin);
        new UserDaoImpl().delete(user);

        assertNull(postDao.selectById(postId));

    }
}
