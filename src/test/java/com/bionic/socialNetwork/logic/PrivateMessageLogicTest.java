package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.PrivateMessageDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.PrivateMessage;
import com.bionic.socialNetwork.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PrivateMessageLogicTest {
    private UserDao userDao;
    private User sent;
    private User receiver;
    private PrivateMessageLogic privateMessageLogic;
    private String msg;
    private PrivateMessage privateMessage;

    @Before
    public void testCreatePm() throws Exception {
        userDao = new UserDaoImpl();
        sent = new User("PM", "", "", "", new Date(0));
        receiver = new User("PM", "", "", "", new Date(0));
        privateMessageLogic = new PrivateMessageLogic();
        msg = "msg";

        userDao.insert(sent, new Password("pass"));
        userDao.insert(receiver, new Password("pass"));

        boolean result = privateMessageLogic
                .createPm(sent.getId(), receiver.getId(), msg);

        assertEquals(result, true);
        List<PrivateMessage> privateMessages =
                new PrivateMessageDaoImpl().selectReceivedNextWith(receiver, 0);
        privateMessage = privateMessages.get(0);
    }

    @Test
    public void testReadPm() throws Exception {
        String result = privateMessageLogic.readPm(receiver.getId(),
                                                 privateMessage.getMessageId());

        assertEquals(privateMessage.getMessage(), result);
    }

    @After
    public void deletePM() throws Exception {
        new PrivateMessageDaoImpl().delete(privateMessage);
        userDao.delete(sent);
        userDao.delete(receiver);
    }
}
