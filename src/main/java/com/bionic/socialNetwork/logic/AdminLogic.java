package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.AdministratorDao;
import com.bionic.socialNetwork.dao.InviteDao;
import com.bionic.socialNetwork.dao.impl.AdministratorDaoImpl;
import com.bionic.socialNetwork.dao.impl.InviteDaoImpl;
import com.bionic.socialNetwork.models.Administrator;
import com.bionic.socialNetwork.models.Invite;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  23.07.14.
 */
public class AdminLogic {
    private static final Logger LOGGER =
            LogManager.getLogger(AdminLogic.class.getName());

    public static boolean verifyAdministrator(long id) {
        Administrator administrator = null;
        AdministratorDao administratorDao = new AdministratorDaoImpl();
        try {
            administrator = administratorDao.selectById(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return administrator != null;
    }

    public static String createInvite() {
        InviteDao inviteDao = new InviteDaoImpl();
        String invite = generateInvite();
        try {
            inviteDao.insert(new Invite(invite));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return invite;
    }

    private static String generateInvite() {
        return RandomStringUtils.random(5, true, true);
    }
}
