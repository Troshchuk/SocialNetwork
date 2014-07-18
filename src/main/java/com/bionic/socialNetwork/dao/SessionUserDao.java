package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.SessionUser;

/**
 * @author Denis
 * @version 1.00  18.07.2014.
 */
public interface SessionUserDao {

    public void insert(SessionUser sessionUser) throws Exception;

    public SessionUser selectById(long id) throws Exception;

    public SessionUser selectBySession(String session) throws Exception;

    public void delete(SessionUser sessionUser) throws Exception;
}
