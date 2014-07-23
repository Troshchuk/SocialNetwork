package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.User;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Collection;
import java.util.List;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  18.07.14.
 */
public class UserList {
    @JsonIgnore
    private long beginId;
    @JsonIgnore
    private UserDao userDao;

    private Collection<User> users;

    public UserList(long beginId) {
        this.beginId = beginId;
        userDao = new UserDaoImpl();
    }

    public void next() {
        try {
            List<User> users = userDao.selectNext(beginId);
            beginId += users.size();
            this.users = users;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<User> getUsers() {
        return users;
    }
}
