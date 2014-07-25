package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.User;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Denis Biyovskiy
 * @version 1.00  23.07.2014.
 */
public class FollowingUsersList {

    private List<User> followingUsers;

    public FollowingUsersList(long id, int page) {
        UserDao userDao = new UserDaoImpl();
        try {
            followingUsers = userDao.selectFriendsNext(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getFollowingUsers() {
        return followingUsers;
    }
}
