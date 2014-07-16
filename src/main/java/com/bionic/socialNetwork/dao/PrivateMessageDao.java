package com.bionic.socialNetwork.dao;

/**
 * Private messages Dao
 *
 * @author Denis Biyovskiy
 * @version 1.00  16.07.2014.
 */
public interface PrivateMessageDao {
    public String getMessage(long userId) throws Exception;

    public void setMessage(long userId) throws Exception;
}
