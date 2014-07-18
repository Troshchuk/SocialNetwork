package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.AddUserDao;
import com.bionic.socialNetwork.models.AddUser;
import com.bionic.socialNetwork.util.HibernateUtil;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author Denis
 * @version 1.00  18.07.2014.
 */
public class AddUserDaoImplTest {
    private AddUserDao addUserDao;
    private long addUserId;
    private String invite = "root";

    public AddUserDaoImplTest() {
    }

    @Before
    public void addUserForTest() throws Exception {
        addUserDao = new AddUserDaoImpl();
        AddUser addUser = new AddUser(invite);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(addUser);
        session.getTransaction().commit();
        session.close();
        addUserId = addUser.getAddUserId();
        assertEquals(addUserId, addUserDao.selectByInvite(invite).getAddUserId());
    }

    @Test
    public void testSelectByInvite() throws Exception {
        AddUser addUser = addUserDao.selectByInvite(invite);
        assertNotNull(addUser);
    }

    @After
    public void testDelete() throws Exception {
        addUserDao.delete(addUserDao.selectByInvite(invite));
        assertNull(addUserDao.selectById(addUserId));
    }
}
