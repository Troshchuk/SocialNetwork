package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.InviteDao;
import com.bionic.socialNetwork.models.Invite;
import com.bionic.socialNetwork.util.HibernateUtil;
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
public class InviteDaoImplTest {
    private InviteDao inviteDao;
    private long inviteId;
    private String inviteStr = "root";

    public InviteDaoImplTest() {
    }

    @Before
    public void inviteForTest() throws Exception {
        inviteDao = new InviteDaoImpl();
        Invite invite = new Invite(inviteStr);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(invite);
        session.getTransaction().commit();
        session.close();
        inviteId = invite.getInviteId();
        assertEquals(inviteId, inviteDao.selectByInvite(inviteStr).getInviteId());
    }

    @Test
    public void testSelectByInvite() throws Exception {
        Invite invite = inviteDao.selectByInvite(inviteStr);
        assertNotNull(invite);
    }

    @After
    public void testDelete() throws Exception {
        inviteDao.delete(inviteDao.selectByInvite(inviteStr));
        assertNull(inviteDao.selectById(inviteId));
    }
}
