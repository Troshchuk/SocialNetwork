package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.User;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Collection;
import java.util.List;

/**
 * @author Denis Biyovskiy
 * @version 1.00  28.07.2014.
 */
public class FollowingUsersListByName {

    @JsonIgnore
    private long beginId;
    @JsonIgnore
    private UserDao userDao;
    @JsonIgnore
    private String name;
    @JsonIgnore
    private String surname;

    private List<User> followingUsers;

    public FollowingUsersListByName(String name, String surname, long id, int beginId) {
        this.name = name;
        this.surname = surname;
        this.beginId = beginId;
        UserDao userDao = new UserDaoImpl();
        try {
            followingUsers = userDao.selectFollowingsByName(name, surname, id, beginId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getFollowingUsers() {
        return followingUsers;
    }
}
