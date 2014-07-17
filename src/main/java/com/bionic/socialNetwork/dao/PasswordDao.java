package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.Password;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  15.07.14.
 */
public interface PasswordDao {
    public Password selectById(long id) throws Exception;
    public void update(Password password) throws Exception;
}
