package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.PostDao;
import com.bionic.socialNetwork.dao.impl.PostDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Post;
import com.bionic.socialNetwork.models.User;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Collection;
import java.util.List;

/**
 * @author Denis Biyovskiy
 * @version 1.00  21.07.2014.
 */
public class PostsList {
    private Collection<Post> posts;

    public PostsList(long id, int page) {
        try {
            PostDao postDao = new PostDaoImpl();
            User user = new UserDaoImpl().selectById(id);
            postDao.selectLastWith(user, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<Post> getPosts() {
        return posts;
    }
}
