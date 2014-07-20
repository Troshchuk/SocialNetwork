package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.Group;

/**
 * @author yoalex5
 * @version 1.0 17.07.14
 */
public interface GroupDao {
    public Group selectById(long id) throws Exception;
    public void insert(Group group) throws Exception;
    public void update(Group group) throws Exception;
    public void delete(Group group) throws Exception;
}
