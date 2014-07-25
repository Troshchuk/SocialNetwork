package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.PrivateMessageDao;
import com.bionic.socialNetwork.dao.impl.PrivateMessageDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.PrivateMessage;
import com.bionic.socialNetwork.models.User;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  25.07.14.
 */
public class PrivateMessageLogic {
    public boolean createPm(long id, long toUserId, String msg) {
        try {
            PrivateMessageDao privateMessageDao = new PrivateMessageDaoImpl();
            User user = new UserDaoImpl().selectById(id);
            User toUser = new UserDaoImpl().selectById(toUserId);
            PrivateMessage post = new PrivateMessage(user, toUser, msg,
                                      new Timestamp(new Date().getTime()));
            privateMessageDao.insert(post);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
