package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.lists.FollowingUsersList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  01.08.14.
 */


@Path("followings")
public class FollowingController {
    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getPage(@Context ServletContext context) {
        return context.getResourceAsStream("/WEB-INF/pages/followings.html");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getFollowings{page}")
    public FollowingUsersList getNextUsers(@Context HttpServletRequest request,
                                           @PathParam("page") int page) {
        return new FollowingUsersList((Long) request.getAttribute("userId"),
                                      page);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getFollowings{fullname}/{page}")
    public FollowingUsersList search(@Context HttpServletRequest request,
                                     @PathParam("fullname") String fullName,
                                     @PathParam("page") int page) {
        return new FollowingUsersList(fullName,
                                      (Long) request.getAttribute("userId"),
                                      page);
    }
}
