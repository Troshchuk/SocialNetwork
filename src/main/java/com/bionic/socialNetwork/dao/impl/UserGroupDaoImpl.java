package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.AbstractGroupDAO;
import com.bionic.socialNetwork.models.UserGroup;

/**
 * @author yoalex5
 * @version 1.0 17.07.14
 */
public class UserGroupDaoImpl<T> extends AbstractGroupDAO {

    public UserGroupDaoImpl() {
        super((Class<T>) UserGroup.class);
    }
}
