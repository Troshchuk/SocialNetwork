package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.PostDao;
import com.bionic.socialNetwork.dao.impl.PostDaoImpl;
import com.bionic.socialNetwork.models.Post;

import java.util.List;

/**
 * @author Denis Biyovskiy
 * @version 1.00  21.07.2014.
 */
public class PostsList {
    private int beginId;
    private PostDao postDao;

    public PostsList() {
        beginId = 0;
        postDao = new PostDaoImpl();
    }

    public List<Post> getPostsList() {
        try {
            List<Post> posts = postDao.selectNext(beginId);
            beginId += posts.size();
            return posts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
