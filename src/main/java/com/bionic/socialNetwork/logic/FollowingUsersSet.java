package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.User;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Denis Biyovskiy
 * @version 1.00  23.07.2014.
 */
public class FollowingUsersSet {
    @JsonIgnore
    private User user;
    @JsonIgnore
    private UserDao userDao;
    @JsonIgnore
    private int userId;
    @JsonIgnore
    private int beginId;

    private Set<User> followingUsers;

    public FollowingUsersSet(int beginId, int userId) {
        userDao = new UserDaoImpl();
        followingUsers = new HashSet<User>();
        this.beginId = beginId;
        this.userId = userId;
    }

    public void next() {
        try {
            user = userDao.selectById(userId);
            Set<User> followingUsersAll = user.getFriends();
            Iterator itr = followingUsersAll.iterator();
            if (beginId > 1) {
                for (int i = 0; i < beginId; i++, itr.next()) ;
            }
            for (int i = beginId; i < beginId + 10; i++) {
                followingUsers.add((User) itr.next());
            }
            followingUsers = followingUsersAll;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<User> getFollowingUsers() {
        return followingUsers;
    }
}
