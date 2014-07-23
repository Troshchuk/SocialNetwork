package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.dao.AdministratorDao;
import com.bionic.socialNetwork.dao.impl.AdministratorDaoImpl;
import com.bionic.socialNetwork.logic.SessionController;
import com.bionic.socialNetwork.models.Administrator;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  23.07.14.
 */
@Path("admin")
public class AdminController {
//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    public void method() {
//        ..HttpSession session = request.getSession();
//        String sessionUser = (String) session.getAttribute("user");
//        SessionController sessionController = new SessionController();
//        long userId = sessionController.verifySession(sessionUser);
//        AdministratorDao administratorDao = new AdministratorDaoImpl();
//        Administrator administrator = administratorDao.selectById(userId);
//    }
}
