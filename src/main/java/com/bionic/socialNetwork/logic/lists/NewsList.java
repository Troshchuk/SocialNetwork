package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.impl.BackOfficeAdminDaoImpl;
import com.bionic.socialNetwork.dao.impl.PostDaoImpl;
import com.bionic.socialNetwork.models.Post;
import org.codehaus.jackson.annotate.JsonIgnore;


import java.util.Collection;

/**
 * NewsList logic
 *
 * @author Matvey Mitnitskyi
 * @version 1.00 created 23.07.2014.
 */
public class NewsList {
    @JsonIgnore
    private int beginIndex = 0;
    @JsonIgnore
    private PostDaoImpl postDao;

    private Collection<Post> posts;

    public NewsList(int begin) {
        this.beginIndex = begin;
        postDao = new PostDaoImpl();
        next();
    }

    public void next() {
        try {
            BackOfficeAdminDaoImpl backOfficeAdminDao =
                    new BackOfficeAdminDaoImpl();
            posts = postDao.selectLastBeckOffWith(backOfficeAdminDao.selectAll(),
                                                  beginIndex);
            beginIndex += (long) posts.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }
}