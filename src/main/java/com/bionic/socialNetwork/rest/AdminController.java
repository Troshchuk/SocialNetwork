package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.AdminLogic;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
    @Context
    private ServletContext context;
    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getAdminPage(@Context HttpServletRequest request) {
        long userId = (Long)request.getAttribute("userId");
        if (AdminLogic.verifyAdministrator(userId)) {
            return context.getResourceAsStream("/WEB-INF/pages/admin.html");
        } else {
            return context.getResourceAsStream("/WEB-INF/pages/user.html");
        }
    }

    @GET
    @Path("createInvite")
    @Produces(MediaType.APPLICATION_JSON)
    public String createInvite(@Context HttpServletRequest request) {
        long userId = (Long)request.getAttribute("userId");
        if (AdminLogic.verifyAdministrator(userId)) {
            String invite = AdminLogic.createInvite();
            return "{\"invite\": " + "\"" + invite + "\"}";
        } else {
            return "{\"invite\": null}";
        }
    }

}
