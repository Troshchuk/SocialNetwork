package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.Invite;

/**
 * @author Denis
 * @version 1.0 18.07.14
 */
public interface InviteDao {
    public void insert(Invite invite) throws Exception;
    public void delete(Invite invite) throws Exception;
    public Invite selectById(long addUserId) throws Exception;
    public Invite selectByInvite(String invite) throws Exception;
}
