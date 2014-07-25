package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.GroupDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.GroupDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.models.User;

import java.util.List;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  25.07.14.
 */
public class UserGroupsList {

    private List<Group> groups;

    public UserGroupsList(long id, int page) {
        UserDao userDao = new UserDaoImpl();
        try {
            groups = userDao.selectUserGroupsNext(id, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Group> getGroups() {
        return groups;
    }
}