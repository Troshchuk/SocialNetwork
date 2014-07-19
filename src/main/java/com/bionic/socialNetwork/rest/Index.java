package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.Login;
import com.bionic.socialNetwork.logic.SessionController;
import com.bionic.socialNetwork.logic.UsersList;
import com.bionic.socialNetwork.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.Collection;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  14.07.14.
 */
@Path("index")
public class Index {
    @GET
    @Path("getPage")
    @Produces(MediaType.TEXT_HTML)
    public InputStream test(@Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        String sessionUser = (String) session.getAttribute("user");
        SessionController sessionController = new SessionController();
        long userId = sessionController.verifySession(sessionUser);
        if (userId == -1) {
            return session.getServletContext()
                          .getResourceAsStream("/WEB-INF/pages/login.html");
        } else {
            return session.getServletContext()
                          .getResourceAsStream("/WEB-INF/pages/list.html");
        }

    }

}