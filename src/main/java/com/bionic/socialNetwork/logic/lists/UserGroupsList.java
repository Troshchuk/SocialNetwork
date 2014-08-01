package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.GroupDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.GroupDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  25.07.14.
 */
public class UserGroupsList {
    private List<Group> groups;

    @JsonIgnore
    private boolean resolved;

    @JsonIgnore
    private static final Logger LOGGER =
            LogManager.getLogger(UserGroupsList.class.getName());

    public UserGroupsList(long id, int page) {
        UserDao userDao = new UserDaoImpl();
        try {
            groups = userDao.selectUserGroupsNext(id, page);
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

    public List<Group> getGroups() {
        return groups;
    }

    public boolean isResolved() {
        return resolved;
    }
}