package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.AbstractGroupDAO;
import com.bionic.socialNetwork.models.GroupPost;

/**
 * @author yoalex5
 * @version 1.0 16.07.14
 */
public class GroupPostDaoImpl<T> extends AbstractGroupDAO {

    public GroupPostDaoImpl() {
        super((Class<T>) GroupPost.class);
    }
}
