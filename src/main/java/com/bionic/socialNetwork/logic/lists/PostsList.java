package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.PostDao;
import com.bionic.socialNetwork.dao.impl.PostDaoImpl;
import com.bionic.socialNetwork.models.Post;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Collection;
import java.util.List;

/**
 * @author Denis Biyovskiy
 * @version 1.00  21.07.2014.
 */
public class PostsList {
    @JsonIgnore
    private int beginId;
    @JsonIgnore
    private PostDao postDao;

    private Collection<Post> posts;

    public PostsList(int beginId) {
        this.beginId = beginId;
        postDao = new PostDaoImpl();
    }

    public void next() {
        try {
            List<Post> posts = postDao.selectNext(beginId);
            beginId += posts.size();
            this.posts = posts;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<Post> getPosts() {
        return posts;
    }
}
