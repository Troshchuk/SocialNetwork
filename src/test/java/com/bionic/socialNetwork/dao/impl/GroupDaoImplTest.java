package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.GroupDAO;
import com.bionic.socialNetwork.models.Group;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test Group's DAO
 *
 * @author yoalex5
 * @version 1.0 17.07.14
 */
public class GroupDaoImplTest {
    private long id;
    private Group groupActual;
    private GroupDAO<Group> groupDao;

    @Before
    public void testInsertGroup() throws Exception {
        groupActual = new Group("First");
        groupDao = new GroupDaoImpl();
        groupDao.insert(groupActual);

        id = groupActual.getGroupId();
    }

    @Test
    public void testSelectGroupById() throws Exception {
        Group groupExpected = groupDao.selectById(id);
        assertEquals(groupExpected, groupActual);
    }

    @Test
    public void testUpdateGroup() throws Exception {
        groupActual.setName("Updated");
        groupDao.update(groupActual);
        testSelectGroupById();
    }

    @After
    public void testDeleteGroup() throws Exception {
        Group groupDeleted = groupDao.selectById(id);
        groupDao.delete(groupDeleted);
    }
}
