package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.Administrator;
import com.bionic.socialNetwork.models.BackOfficeAdmin;

import java.util.List;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  16.07.14.
 */
public interface BackOfficeAdminDao {
    public void insert(BackOfficeAdmin backOfficeAdmin) throws Exception;

    public void delete(BackOfficeAdmin backOfficeAdmin) throws Exception;

    public List<BackOfficeAdmin> selectAll() throws Exception;

    public BackOfficeAdmin selectById(long id) throws Exception;
}
