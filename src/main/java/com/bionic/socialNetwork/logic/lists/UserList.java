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
 * @author Dmytro Troshchuk
 * @version 1.00  18.07.14.
 */
public class UserList {
    private Collection<User> users;

    @JsonIgnore
    private boolean resolved;

    @JsonIgnore
    private static final Logger LOGGER =
            LogManager.getLogger(UserList.class.getName());

    public UserList(int page) {
        UserDao userDao = new UserDaoImpl();

        try {
            users = userDao.selectNext(page);
        }
        catch (NullPointerException e) {
            resolved = false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            resolved = false;
        }
        resolved = true;
    }

    public UserList(String fullName, int page) {
        UserDao userDao = new UserDaoImpl();
        String[] arr = fullName.split(" ");
        String name = arr[0];
        String surname = arr[1];
        try {
            users = userDao.selectByFullName(name, surname, page);
        }
        catch (NullPointerException e) {
            resolved = false;
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            resolved = false;
        }
        resolved = true;
    }

    public UserList() {

    }

    public UserList(long interest, int page) {
        UserDao userDao = new UserDaoImpl();

        try {
            users = userDao.selectByInterest(interest, page);
        }
        catch (NullPointerException e) {
            resolved = false;
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            resolved = false;
        }
        resolved = true;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public boolean isResolved() {
        return resolved;
    }
}
