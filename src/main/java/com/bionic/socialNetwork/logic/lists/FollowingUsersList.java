package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

/**
 * @author Denis Biyovskiy
 * @version 1.00  23.07.2014.
 */
public class FollowingUsersList {
    private List<User> followingUsers;

    @JsonIgnore
    private static final Logger LOGGER =
            LogManager.getLogger(FollowingUsersList.class.getName());

    public FollowingUsersList(long id, int page) {
        UserDao userDao = new UserDaoImpl();
        try {
            followingUsers = userDao.selectFollowingsNext(page);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public List<User> getFollowingUsers() {
        return followingUsers;
    }
}
