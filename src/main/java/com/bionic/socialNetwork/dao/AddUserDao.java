package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.AddUser;

/**
 * @author Denis
 * @version 1.00  18.07.2014.
 */
public interface AddUserDao {

    public AddUser selectByInvite(String invite) throws Exception;

    public AddUser selectById(long addUserId) throws Exception;

    public void delete(AddUser addUser) throws Exception;
}
