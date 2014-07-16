package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PostDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.Post;
import com.bionic.socialNetwork.models.User;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;

/**
 * @author Denis
 * @version 1.00  16.07.2014.
 */
public class PostDaoImplTest {

    @Test
    public void testSetPost() throws Exception {
        User user = new User("www", "", "", "");
        UserDao userDao = new UserDaoImpl();
        userDao.insert(user);

        Post post = new Post("Succes", user);
        PostDao postDao = new PostDaoImpl();
        postDao.setPost(post);
    }

    @Test
    public void testGetPost() throws Exception {
        long userId = 1;
        PostDao postDao = new PostDaoImpl();
        Post post = postDao.getPost(userId);
    }
}
