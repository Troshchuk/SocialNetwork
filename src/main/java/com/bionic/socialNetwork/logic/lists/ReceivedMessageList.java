package com.bionic.socialNetwork.logic.lists;

import com.bionic.socialNetwork.dao.PrivateMessageDao;
import com.bionic.socialNetwork.dao.impl.PrivateMessageDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.PrivateMessage;
import com.bionic.socialNetwork.models.User;

import java.util.Collection;

/**
 * @author Denis Biyovskiy
 * @version 1.00  23.07.2014.
 */
public class ReceivedMessageList {
    private Collection<PrivateMessage> receivedMessages;

    public ReceivedMessageList(long id, int page) {
        PrivateMessageDao privateMessageDao = new PrivateMessageDaoImpl();
        try {
            User user = new UserDaoImpl().selectById(id);
            receivedMessages =
                    privateMessageDao.selectReceivedNextWith(user, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<PrivateMessage> getPrivateMessages() {
        return receivedMessages;
    }
}


