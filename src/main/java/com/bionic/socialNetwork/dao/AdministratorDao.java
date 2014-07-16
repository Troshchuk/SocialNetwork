package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.Administrator;
import com.bionic.socialNetwork.models.User;

import java.util.List;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  16.07.14.
 */
public interface AdministratorDao {
    public void insert(Administrator administrator) throws Exception;

    public void delete(Administrator administrator) throws Exception;

    public List<Administrator> selectAll() throws Exception;

    public Administrator selectById(long id) throws Exception;
}