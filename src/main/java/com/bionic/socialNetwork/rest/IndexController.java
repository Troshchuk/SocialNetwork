package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.SessionController;
import com.bionic.socialNetwork.models.SessionUser;
import com.sun.jersey.spi.container.ContainerRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  14.07.14.
 */
@Path("index")
public class IndexController {
    @Context
    private ServletContext context;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream test(@Context HttpServletRequest request) {
        Long userID = (Long) request.getAttribute("userId");

        if (userID == null) {
            return context.getResourceAsStream("/WEB-INF/pages/login.html");
        } else {
            return context.getResourceAsStream("/WEB-INF/pages/home.html");
        }


    }
}
