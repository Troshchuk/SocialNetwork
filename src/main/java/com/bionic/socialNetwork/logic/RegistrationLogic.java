package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.InviteDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.InviteDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Invite;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Bish_ua on 17.07.2014.
 */
public class RegistrationLogic {

    private final String NO_AVATAR_PATH = "/avatar/noavatar.png";
    private final String DEFAULT_POSITION = "employee";

    private static final Logger LOGGER =
            LogManager.getLogger(RegistrationLogic.class.getName());

    public String register(String name, String surname, String login,
                           String password, String invite) {
            try {
                if (!checkInviteCode(invite)) {
                    return Responses.JSON_RESPONSE_WRONG_INVITE_CODE;
                }
                if (!match(login, password)) {
                    return Responses.JSON_RESPONSE_WRONG_LOGIN_PASS;
                }
                if (addUser(name, surname, login, password)) {
                    InviteDao inviteDao = new InviteDaoImpl();
                    deleteInvite(invite);
                    return Responses.JSON_RESPONSE_TRUE;
                } else {
                    return Responses.JSON_RESPONSE_FALSE;
                }
            }
            catch (NullPointerException e) {
                return Responses.JSON_RESPONSE_FALSE;
            }
    }


    private boolean addUser(String name, String surname, String login,
                            String password) {
        UserDao userDao = new UserDaoImpl();
        User alreadyUsedLogin = null;

        try {
            alreadyUsedLogin = userDao.selectByLogin(login);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (alreadyUsedLogin == null) {
            User user = new User();
            user.setLogin(login);
            user.setName(name);
            user.setSurname(surname);
            user.setPosition(DEFAULT_POSITION);
            user.setBirthday(new Date(0));
            user.setPathToAvatar(NO_AVATAR_PATH);

            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes(), 0, password.length());
                String md5 = new BigInteger(1, md.digest()).toString(16);
                userDao.insert(user, new Password(md5));
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
        return false;


    }

    public boolean checkInviteCode(String invite) {
        InviteDao inviteDao = new InviteDaoImpl();
        try {
            Invite currentInvite = inviteDao.selectByInvite(invite);
            if (currentInvite != null) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }

    }

    private void deleteInvite(String invite) {
        InviteDao inviteDao = new InviteDaoImpl();
        try {
            Invite currentInvite = inviteDao.selectByInvite(invite);
            inviteDao.delete(currentInvite);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private boolean match(String login, String password) {

        //matching login & password
        Pattern loginPattern = Pattern.
                compile("^([a-z0-9_\\.-]{1,20})@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$");
        Matcher loginMatcher = loginPattern.matcher(login);
        // At least 4 latin symbols or digits and _ -
        Pattern passwordPattern =
                Pattern.compile("^[a-zA-Z0-9_-]{4,16}$");

        //At least one upper case, one digit and consist of 4-10 symbols
        // ("^.*(?=.{4,10})(?=.*\\d)(?=.*[a-zA-Z]).*$");

        Matcher passwordMatcher = passwordPattern.matcher(password);

        if (loginMatcher.matches() && passwordMatcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
