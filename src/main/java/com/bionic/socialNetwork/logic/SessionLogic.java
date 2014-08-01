package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.SessionUserDao;
import com.bionic.socialNetwork.dao.impl.SessionUserDaoImpl;
import com.bionic.socialNetwork.models.SessionUser;
import com.bionic.socialNetwork.models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Bish_ua, Troshchuk Dmitry
 * @version 1.01 18.07.2014.
 */
public class SessionLogic {
    private static final Logger LOGGER =
            LogManager.getLogger(SessionLogic.class.getName());

    public String getNewSession(User user) {
        SessionUserDao sessionDao = new SessionUserDaoImpl();
        String session = generateSession();
        SessionUser sessionUser = new SessionUser(session, user);

        try {
            sessionDao.insert(sessionUser);
        }
        catch (NullPointerException e) {
            return "{\"status\":false}";
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return session;
    }

    /**
     * @param session
     * @return
     */
    public long verifySession(String session) {
        SessionUser sessionUser = null;
        SessionUserDao sessionUserDao = new SessionUserDaoImpl();
        try {
            sessionUser = sessionUserDao.selectBySession(session);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return -1;
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        if (sessionUser != null) {
            return sessionUser.getUser().getId();
        } else {
            return -1;
        }
    }

    public void deleteSession(String session) {
        SessionUser sessionUser = null;
        SessionUserDao sessionUserDao = new SessionUserDaoImpl();
        try {
            sessionUser = sessionUserDao.selectBySession(session);
            if (sessionUser != null) {
                sessionUserDao.delete(sessionUser);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private String generateSession() {
        return RandomStringUtils.random(45, true, true);
    }
}
