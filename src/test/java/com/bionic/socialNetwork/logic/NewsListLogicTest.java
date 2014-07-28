package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.BackOfficeAdminDao;
import com.bionic.socialNetwork.dao.PostDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.BackOfficeAdminDaoImpl;
import com.bionic.socialNetwork.dao.impl.PostDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.logic.lists.NewsList;
import com.bionic.socialNetwork.models.BackOfficeAdmin;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.Post;
import com.bionic.socialNetwork.models.User;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Test for NewsListLogic
 *
 * @ author  Matvey Mitnitskyi
 * @ version 1.00  26.07.14.
 */
public class NewsListLogicTest {
    private User user;
    private long[] id;
    private int postNumber = 20;
    private String login = "matvey@matvey.com";
    private BackOfficeAdmin backOfficeAdmin;
    private PostDao postDao = new PostDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private BackOfficeAdminDao backOfficeAdminDao =
            new BackOfficeAdminDaoImpl();
    private Post postVerify;

    @Before
    public void testInsert() throws Exception {

        user = new User(login, "BackOffUser", " ", " ", new java.sql.Date(0));
        userDao.insert(user, new Password("password"));

        backOfficeAdmin = new BackOfficeAdmin(user.getId());
        backOfficeAdminDao.insert(backOfficeAdmin);

        /**
         * Creating 10 posts belong to User
         * postNumber-1 in loop and 1 outside with thread delay 1000ms
         * the last one "postVerify" always must be the first in ordered list
         * after executing newsList.getPost() method.
         */
        id = new long[postNumber];
        for (int i = 0; i < postNumber - 1; i++) {
            Post post = new Post("News Post", user,
                                 new Timestamp(new Date().getTime()));
            postDao.insert(post);
            id[i] = post.getPostId();
        }

        Thread.sleep(1000);

        /**
         *
         * Creating and saving test variable postVerify.
         */
        postVerify = new Post("Verify Post", user,
                              new Timestamp(new Date().getTime()));
        postDao.insert(postVerify);
        id[postNumber - 1] = postVerify.getPostId();

        assertNotNull(id);
    }

    /**
     * Getting 10 last posts with the help of
     * newsList.getPost() method, comparing result with the test
     * postVerify variable.
     */
    @Test
    public void testNewsList() throws Exception {
        Thread.sleep(1000);
        User user1 =
                new User("dfgfd", "gdf", "gfd", "gdfgd", new java.sql.Date(0));
        userDao.insert(user1, new Password("fds"));
        Post post = new Post("fds", user1, new Timestamp(new Date().getTime()));
        postDao.insert(post);
        NewsList newsList = new NewsList(0);
        List<Post> postPull = newsList.getPosts();
        assertFalse(postPull.isEmpty());
        assertEquals(postVerify.getPostId(), postPull.get(0).getPostId());
        postDao.delete(post);
        userDao.delete(user1);
    }

    @After
    public void testDelete() throws Exception {
        for (int i = 0; i < postNumber; i++) {
            postDao.delete(postDao.selectById(id[i]));
        }
        backOfficeAdminDao.delete(backOfficeAdmin);
        userDao.delete(user);
    }

}