package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Group;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Collection;
import java.util.List;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  01.08.14.
 */
public class GroupList {
    private Collection<Group> groupPosts;

    @JsonIgnore
    private static final Logger LOGGER =
            LogManager.getLogger(GroupPostList.class.getName());

    public GroupList(long id, int page) {
        try {
            List<Group> group =
                    new UserDaoImpl().selectUserGroupsNext(id, page);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
