package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Collection;
import java.util.List;

/**
 * @author Denis Biyovskiy
 * @version 1.00  28.07.2014.
 */
public class FollowingUsersListByName {
    private List<User> followingUsers;

    @JsonIgnore
    private static final Logger LOGGER =
            LogManager.getLogger(FollowingUsersListByName.class.getName());

    public FollowingUsersListByName(String name, String surname, long id,
                                    int beginId) {
        UserDao userDao = new UserDaoImpl();
        try {
            followingUsers =
                    userDao.selectFollowingsByName(name, surname, id, beginId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public List<User> getFollowingUsers() {
        return followingUsers;
    }
}
