package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.Post;

import java.util.List;

/**
 * Posts Dao
 *
 * @author Denis Biyovskiy
 * @version 1.00  16.07.2014.
 */
public interface PostDao {
    public Post selectById(long userId) throws Exception;

    public List<Post> selectNext(long beginId)throws Exception;

    public void insert(Post post) throws Exception;

    public  void delete(Post post) throws  Exception;
}
