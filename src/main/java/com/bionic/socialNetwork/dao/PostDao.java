package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.BackOfficeAdmin;
import com.bionic.socialNetwork.models.Post;
import com.bionic.socialNetwork.models.User;

import java.util.List;

/**
 * Posts Dao
 *
 * @author Denis Biyovskiy
 * @version 1.00  16.07.2014.
 */
public interface PostDao {
    public Post selectById(long userId) throws Exception;
    public void insert(Post post) throws Exception;
    public void delete(Post post) throws Exception;
    public List selectLastWith(User user, int lot) throws Exception;
    public List<Post> selectLastBeckOffWith (List<BackOfficeAdmin> backOfficeAdmins,
                                             int lot) throws Exception;
}
