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
    private Collection<User> users;

    @JsonIgnore
    private static final Logger LOGGER =
            LogManager.getLogger(UserListByName.class.getName());

    public UserListByName(String fullName, int page) {
        UserDao userDao = new UserDaoImpl();
        String[] arr = fullName.split(" ");
        String name = arr[0];
        String surname = arr[1];
        try {
            users = userDao.selectByFullName(name, surname, page);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Collection<User> getUsers() {
        return users;
    }
}
