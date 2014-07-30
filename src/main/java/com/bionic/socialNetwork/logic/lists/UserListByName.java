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
 * @author Biyovskiy Denis
 * @version 1.00  28.07.14.
 */
public class UserListByName {
    @JsonIgnore
    private long beginId;
    @JsonIgnore
    private UserDao userDao;
    @JsonIgnore
    private String name;
    @JsonIgnore
    private String surname;

    private Collection<User> users;

    @JsonIgnore
    private static final Logger LOGGER =
            LogManager.getLogger(UserListByName.class.getName());

    public UserListByName(String name, String surname, long beginId) {
        this.beginId = beginId;
        this.name = name;
        this.surname = surname;
        userDao = new UserDaoImpl();
    }

    public void next() {
        try {
            List<User> users = userDao.selectByName(name, surname, beginId);
            beginId += users.size();
            this.users = users;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Collection<User> getUsers() {
        return users;
    }
}
