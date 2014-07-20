package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.GroupPost;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  20.07.14.
 */
public interface GroupPostDao {
    public GroupPost selectById(long id) throws Exception;
    public void insert(GroupPost group) throws Exception;
    public void update(GroupPost group) throws Exception;
    public void delete(GroupPost group) throws Exception;
}
