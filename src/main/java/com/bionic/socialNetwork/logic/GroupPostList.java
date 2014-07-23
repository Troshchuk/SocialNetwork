package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.GroupDao;
import com.bionic.socialNetwork.dao.impl.GroupDaoImpl;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.models.GroupPost;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Collection;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  23.07.14.
 */
public class GroupPostList {
    private Collection<GroupPost> groupPosts;

    public GroupPostList(long id) {
        try {
            GroupDao groupDao = new GroupDaoImpl();
            Group group = groupDao.selectById(id);
            groupPosts = group.getGroupPosts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<GroupPost> getGroupPosts() {
        return groupPosts;
    }
}
