package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.GroupDao;
import com.bionic.socialNetwork.dao.GroupPostDao;
import com.bionic.socialNetwork.dao.impl.GroupDaoImpl;
import com.bionic.socialNetwork.dao.impl.GroupPostDaoImpl;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.models.GroupPost;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Collection;
import java.sql.Date;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  23.07.14.
 */
public class GroupPostList {
    private Collection<GroupPost> groupPosts;

    public GroupPostList(long id, int page) {
        try {
            GroupPostDao groupPostDao = new GroupPostDaoImpl();
            Group group = new GroupDaoImpl().selectById(id);
            groupPostDao.selectLastWith(group, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<GroupPost> getGroupPosts() {
        return groupPosts;
    }
}
