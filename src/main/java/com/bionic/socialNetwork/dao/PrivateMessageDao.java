package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.PrivateMessage;

/**
 * Private messages Dao
 *
 * @author Denis Biyovskiy
 * @version 1.00  16.07.2014.
 */
public interface PrivateMessageDao {
    public PrivateMessage selectBySentId(long userId) throws Exception;
    public PrivateMessage selectByReceiverId(long userId) throws Exception;
    public void insert(PrivateMessage privateMessage) throws Exception;
}
