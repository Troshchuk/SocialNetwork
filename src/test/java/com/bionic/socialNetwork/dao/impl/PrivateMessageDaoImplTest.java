package com.bionic.socialNetwork.dao.impl;

import com.bionic.socialNetwork.dao.PrivateMessageDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.PrivateMessage;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Denis Biyovskiy, Dmytro Troshchuk
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
        sentUser = new User("PostUser", "", "", "", new java.sql.Date(0));
        userDao.insert(sentUser, new Password("root"));
        receivedUser = new User("PostUser", "", "", "", new java.sql.Date(0));
        userDao.insert(receivedUser, new Password("root"));

        privateMessageDao = new PrivateMessageDaoImpl();
        PrivateMessage privateMessage =
                new PrivateMessage(sentUser, receivedUser, "Some message",
                                   new Timestamp(new Date().getTime()));
        privateMessageDao.insert(privateMessage);

        messageId = privateMessage.getMessageId();
        assertEquals(privateMessage.getMessageId(),
                     privateMessageDao.selectById(messageId).getMessageId());
    }

    @Test
    public void testSelectReceivedNextWith() throws Exception {
        privateMessageDao = new PrivateMessageDaoImpl();
        List<PrivateMessage> privateMessages = new ArrayList<PrivateMessage>();

        PrivateMessage privateMessage =
                new PrivateMessage(sentUser, receivedUser, "fsdf",
                                   new Timestamp(new Date().getTime()));

        Thread.sleep(1000);

        for (int i = 0; i < 9; i++) {
            privateMessages.add(new PrivateMessage(sentUser, receivedUser,
                                                   "selectLast", new Timestamp(
                    new Date().getTime())
            ));
            privateMessageDao.insert(privateMessages.get(i));

        }


        privateMessageDao.insert(privateMessage);
        List<PrivateMessage> result =
                privateMessageDao.selectReceivedNextWith(receivedUser, 1);

        assertEquals(result.get(0).getMessageId(),
                     privateMessage.getMessageId());

        for (int i = 0; i < 9; i++) {
            privateMessageDao.delete(privateMessages.get(i));
        }

        privateMessageDao.delete(privateMessage);
    }

    @After
    public void testDelete() throws Exception {
        privateMessageDao
                .delete(privateMessageDao.selectById(messageId));
        new UserDaoImpl().delete(sentUser);
        new UserDaoImpl().delete(receivedUser);
        assertNull(privateMessageDao.selectById(messageId));
    }

}