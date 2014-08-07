package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.AdminLogic;
import com.bionic.socialNetwork.logic.lists.UserList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  04.08.14.
 */

@Path("interest{id}")
public class InterestsController {
    @Context
    private ServletContext context;
    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getAdminPage(@Context HttpServletRequest request) {

            return context.getResourceAsStream("/WEB-INF/pages/interest.html");

    }

    @GET
    @Path("{page}")
    public UserList search(@PathParam("id") long id,
                           @PathParam("page") int page) {
        return new UserList(id, page);
    }
}
