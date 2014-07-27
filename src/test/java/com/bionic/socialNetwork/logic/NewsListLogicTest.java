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
 * Created by Matvey on 26.07.2014.
 */
public class NewsListLogicTest {
    private User user;
    private String login = "matvey@matvey.com";
    private BackOfficeAdmin backOfficeAdmin;
    private PostDao postDao = new PostDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private BackOfficeAdminDao backOfficeAdminDao =
            new BackOfficeAdminDaoImpl();
    private Post post;

    @Before
    public void testInsert() throws Exception {

        user = new User(login, "BackOffUser", " ", " ");
        userDao.insert(user, new Password("password"));

        backOfficeAdmin = new BackOfficeAdmin(user.getId());
        backOfficeAdminDao.insert(backOfficeAdmin);

        post = new Post("News Post", user,
                             new Timestamp(new Date().getTime()));
        postDao.insert(post);
        assertNotNull(post);
    }

    @Test
    public void testNewsList() throws Exception{
        NewsList newsList = new NewsList(1);
        List<Post> postPull =  newsList.getPosts();
        assertFalse(postPull.isEmpty());
        assertEquals(post , postPull.get(0));
    }
    @After
    public void testDelete() throws Exception {
        postDao.delete(post);
        backOfficeAdminDao.delete(backOfficeAdmin);
        userDao.delete(user);
        }

}