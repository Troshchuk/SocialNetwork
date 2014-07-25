package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.PostDao;
import com.bionic.socialNetwork.dao.impl.PostDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Post;
import com.bionic.socialNetwork.models.User;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  24.07.14.
 */
public class UserLogic {
    public User getUser(long id) {
        User user = null;
        try {
            user = new UserDaoImpl().selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean createPost(long id, String msg) {
        try {
            PostDao postDao = new PostDaoImpl();
            Post post = new Post(msg, new UserDaoImpl().selectById(id),
                                 new Timestamp(new Date().getTime()));
            postDao.insert(post);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
