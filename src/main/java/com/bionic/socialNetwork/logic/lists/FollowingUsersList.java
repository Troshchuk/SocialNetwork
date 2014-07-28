package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.User;

import java.util.List;

/**
 * @author Denis Biyovskiy
 * @version 1.00  23.07.2014.
 */
public class FollowingUsersList {

    private List<User> followingUsers;

    public FollowingUsersList(long id, int page) {
        UserDao userDao = new UserDaoImpl();
        try {
            followingUsers = userDao.selectFollowingsNext(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getFollowingUsers() {
        return followingUsers;
    }
}
