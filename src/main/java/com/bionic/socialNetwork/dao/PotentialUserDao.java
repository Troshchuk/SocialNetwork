package com.bionic.socialNetwork.dao;

import com.bionic.socialNetwork.models.PotentialUser;

/**
 * @author yoalex5
 * @version 1.0 18.07.14
 */
public interface PotentialUserDao {
    public void insert(PotentialUser potentionalUser) throws Exception;
    public void delete(PotentialUser potentionalUser) throws Exception;
}
