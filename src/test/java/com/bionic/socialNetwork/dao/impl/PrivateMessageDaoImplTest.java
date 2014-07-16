package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PrivateMessageDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.PrivateMessage;
import com.bionic.socialNetwork.models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Denis
 * @version 1.00  16.07.2014.
 */
public class PrivateMessageDaoImplTest {
    private User sentUser;
    private User receivedUser;
    private PrivateMessageDao privateMessageDao;
    private long messageId;

    @Before
    @Test
    public void testInsert() throws Exception {
        UserDao userDao = new UserDaoImpl();
        sentUser = new User("PostUser", "", "", "");
        userDao.insert(sentUser);
        receivedUser = new User("PostUser", "", "", "");
        userDao.insert(receivedUser);

        privateMessageDao = new PrivateMessageDaoImpl();
        PrivateMessage privateMessage = new PrivateMessage(sentUser, receivedUser, "Some message");
        privateMessageDao.insert(privateMessage);

        messageId = privateMessage.getMessageId();
        assertEquals(privateMessage.getMessageId(), privateMessageDao.selectBySentId(messageId).getMessageId());
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
}
