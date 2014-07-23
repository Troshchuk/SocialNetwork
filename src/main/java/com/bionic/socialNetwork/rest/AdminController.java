package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.Admin;
import com.bionic.socialNetwork.logic.SessionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  23.07.14.
 */
@Path("admin")
public class AdminController {
    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getAdminPage(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionUser = (String) session.getAttribute("user");
        SessionController sessionController = new SessionController();
        long userId = sessionController.verifySession(sessionUser);
        if (Admin.verifyAdministrator(userId)) {
            return session.getServletContext()
                          .getResourceAsStream("/WEB-INF/pages/admin.html");
        } else {
            return session.getServletContext()
                          .getResourceAsStream("/WEB-INF/pages/home.html");
        }
    }

    @GET
    @Path("createInvite")
    @Produces(MediaType.APPLICATION_JSON)
    public String createInvite(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionUser = (String) session.getAttribute("user");
        SessionController sessionController = new SessionController();
        long userId = sessionController.verifySession(sessionUser);
        if (Admin.verifyAdministrator(userId)) {
            String invite = Admin.createInvite();
            return "{\"invite\": " + invite + "\"}";
        } else {
            return "{\"invite\": null}";
        }
    }

}
