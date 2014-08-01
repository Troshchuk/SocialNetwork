package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.PrivateMessageDao;
import com.bionic.socialNetwork.dao.impl.PrivateMessageDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.PrivateMessage;
import com.bionic.socialNetwork.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  25.07.14.
 */
public class PrivateMessageLogic {
    private static final Logger LOGGER =
            LogManager.getLogger(PrivateMessageLogic.class.getName());

    public boolean createPm(long id, long toUserId, String msg) {
        try {
            PrivateMessageDao privateMessageDao = new PrivateMessageDaoImpl();
            User user = new UserDaoImpl().selectById(id);
            User toUser = new UserDaoImpl().selectById(toUserId);
            PrivateMessage pm = new PrivateMessage(user, toUser, msg,
                                                     new Timestamp(new Date()
                                                                           .getTime()));
            privateMessageDao.insert(pm);
            return true;
        }
        catch (NullPointerException e) {
            return false;
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    public String readPm(long userId, long msgId) {
        try {
            User user = new UserDaoImpl().selectById(userId);
            PrivateMessageDao privateMessageDao = new PrivateMessageDaoImpl();
            PrivateMessage pm = privateMessageDao.selectById(msgId);
            if (pm.getReceiverUser().getId() == userId) {
                pm.setRead(true);
                privateMessageDao.update(pm);
                return pm.getMessage();
            } else if (pm.getSentUser().getId() == userId) {
                return pm.getMessage();
            }

            return "";
        }
        catch (NullPointerException e) {
            return "false}";
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            return "";
        }
    }
}
