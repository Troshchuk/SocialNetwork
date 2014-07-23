package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.GroupDao;
import com.bionic.socialNetwork.dao.impl.GroupDaoImpl;
import com.bionic.socialNetwork.models.Group;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  23.07.14.
 */
public class GroupLogic {
    private GroupDao groupDao;

    public GroupLogic() {
        groupDao = new GroupDaoImpl();
    }

    public Group getGroup(long id) {
        try {
            return groupDao.selectById(id);
        } catch (Exception e) {
            return null;

        }
    }
}
