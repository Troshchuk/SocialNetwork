package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.InviteDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.InviteDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Invite;
import com.bionic.socialNetwork.models.Password;
import com.bionic.socialNetwork.models.User;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by Bish_ua on 17.07.2014.
 */
public class Registration {


    public boolean addUser(String name, String surname, String login,
                           String password) {
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setLogin(login);
        user.setName(name);
        user.setSurname(surname);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes(), 0, password.length());
            String md5 = new BigInteger(1, md.digest()).toString(16);
            userDao.insert(user, new Password(md5));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    public boolean checkInviteCode(String invite) {
        InviteDao inviteDao = new InviteDaoImpl();
        try {
            Invite currentInvite = inviteDao.selectByInvite(invite);
            if (currentInvite != null) {
                deleteInvite(currentInvite);
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

    }

    private void deleteInvite(Invite invite) {
        InviteDao inviteDao = new InviteDaoImpl();
        try {
            inviteDao.delete(invite);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
