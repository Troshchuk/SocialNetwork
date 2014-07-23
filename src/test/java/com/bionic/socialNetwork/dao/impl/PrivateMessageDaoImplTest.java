package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PrivateMessageDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.PrivateMessage;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Denis Biyovskiy
 * @version 1.00  16.07.2014.
 */
public class PrivateMessageDaoImplTest {
    private User sentUser;
    private User receivedUser;
    private PrivateMessageDao privateMessageDao;
    private long messageId;

    @Before
    public void testInsert() throws Exception {
        UserDao userDao = new UserDaoImpl();
        sentUser = new User("PostUser", "", "", "");
        userDao.insert(sentUser, new Password("root"));
        receivedUser = new User("PostUser", "", "", "");
        userDao.insert(receivedUser, new Password("root"));

        privateMessageDao = new PrivateMessageDaoImpl();
        PrivateMessage privateMessage = new PrivateMessage(sentUser, receivedUser, "Some message", new Date(new java.util.Date().getTime()));
        privateMessageDao.insert(privateMessage);

        messageId = privateMessage.getMessageId();
        assertEquals(privateMessage.getMessageId(), privateMessageDao.selectBySentId(messageId).getMessageId());
        assertEquals(privateMessage.getMessageId(), privateMessageDao.selectByReceiverId(messageId).getMessageId());
    }


    @Test
    public void testSelectByReceiverId() throws Exception {
        PrivateMessage privateMessage = privateMessageDao.selectByReceiverId(messageId);
        assertNotNull(privateMessage);
    }

    @Test
    public void testSelectBySentId() throws Exception {
        PrivateMessage privateMessage = privateMessageDao.selectBySentId(messageId);
        assertNotNull(privateMessage);
    }

    @Test
    public void testSelectNextSentId() throws Exception {
        List<PrivateMessage> privateMessages = privateMessageDao.selectNextSentId(sentUser.getId());
        assertEquals(messageId, privateMessages.get(0).getMessageId());
    }

    @Test
    public void testSelectReceiverId() throws Exception {
        List<PrivateMessage> privateMessages = privateMessageDao.selectNextReceiverId(receivedUser.getId());
        assertEquals(messageId, privateMessages.get(0).getMessageId());
    }

    @After
    public void testDelete() throws Exception {
        privateMessageDao.delete(privateMessageDao.selectBySentId(messageId));
        new UserDaoImpl().delete(sentUser);
        new UserDaoImpl().delete(receivedUser);
        assertNull(privateMessageDao.selectBySentId(messageId));
    }

}