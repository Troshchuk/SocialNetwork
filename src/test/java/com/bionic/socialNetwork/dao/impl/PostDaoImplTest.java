package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PostDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.Post;
import com.bionic.socialNetwork.models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Denis
 * @version 1.00  16.07.2014.
 */
public class PostDaoImplTest {
    private User user;
    private PostDao postDao;
    private long postId;

    @Before
    @Test
    public void testInsert() throws Exception {
        UserDao userDao = new UserDaoImpl();
        user = new User("PostUser", "", "", "");
        userDao.insert(user);

        postDao = new PostDaoImpl();
        Post post = new Post("Some Post", user);
        postDao.insert(post);

        postId = post.getPostId();
        assertEquals(post.getPostId(), postDao.selectById(postId).getPostId());
    }

    @Test
    public void testSelectById() throws Exception {
        Post post = postDao.selectById(postId);
        assertNotNull(post);
    }
}
