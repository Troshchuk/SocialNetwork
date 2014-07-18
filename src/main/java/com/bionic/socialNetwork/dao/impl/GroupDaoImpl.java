package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.AbstractGroupDAO;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.util.HibernateUtil;
import org.hibernate.Session;

/**
 * @author yoalex5
 * @version 1.0 17.07.14
 */
public class GroupDaoImpl<T> extends AbstractGroupDAO {

    public GroupDaoImpl() {
        super((Class<T>) Group.class);
    }
}
