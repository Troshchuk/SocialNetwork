package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.AbstractGroupDAO;
import com.bionic.socialNetwork.dao.GroupDAO;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;
import com.bionic.socialNetwork.models.UserGroup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test User group's DAO
 *
 * @author yoalex5
 * @version 1.0 18.07.14
 */
public class UserGroupDaoImplTest {

    private UserGroup userGroupActual;
    private GroupDAO<UserGroup> userGroupDao;

    private User user;
    private UserDaoImpl userDao;

    private Group group;
    private GroupDAO<Group> groupDao;


    @Before
    public void beforeTest() throws Exception {
        user = new User("PostUser", "", "", "");
        userDao = new UserDaoImpl();
        userDao.insert(user, new Password("password"));

        group = new Group("First");
        groupDao = new GroupDaoImpl();
        groupDao.insert(group);

        userGroupActual = new UserGroup();
        userGroupActual.setUserId(user.getId());
        userGroupActual.setGroupId(group.getGroupId());

        userGroupDao = new UserGroupDaoImpl();
    }

    @Test
    public void testInsertUserGroup() throws Exception {
        userGroupDao.insert(userGroupActual);
    }

    @After
    public void afterTest() throws Exception {
        userGroupDao.delete(userGroupActual);
        groupDao.delete(group);
        userDao.delete(user);
    }


}
