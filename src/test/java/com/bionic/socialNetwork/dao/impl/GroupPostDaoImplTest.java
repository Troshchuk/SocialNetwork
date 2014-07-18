package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.GroupDAO;

import com.bionic.socialNetwork.models.GroupPost;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;


/**
 * Test GroupPost's DAO
 *
 * @author yoalex5
 * @version 1.0 16.07.14
 */
public class GroupPostDaoImplTest {

    private long id;
    private GroupPost groupPostActual;
    private GroupDAO<GroupPost> groupPostDao;
    private GroupDaoImplTest groupDaoImplTest;

    @Before
    public void preExecute() throws Exception {
        groupDaoImplTest = new GroupDaoImplTest();
        groupDaoImplTest.testInsertGroup();
        groupPostDao = new GroupPostDaoImpl();
        groupPostActual = new GroupPost(groupDaoImplTest.getId(), 1, "1 Post");

        testInsertGroupPost();
    }

    @Test
    public void testSelectGroupPostById() throws Exception {
        GroupPost groupPostExpected = groupPostDao.selectById(id);
        assertEquals(groupPostExpected, groupPostActual);
    }


    @After
    public void postExecute() throws Exception {
        GroupPost groupPostDeleted = groupPostDao.selectById(id);
        groupPostDao.delete(groupPostDeleted);
        groupDaoImplTest.testDeleteGroup();
    }

    private void testInsertGroupPost() throws Exception {
        groupPostDao.insert(groupPostActual);
        id = groupPostActual.getGroupPostId();
    }

}
