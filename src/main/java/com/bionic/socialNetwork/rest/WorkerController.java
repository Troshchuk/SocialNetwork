package com.bionic.socialNetwork.rest;

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
 * @version 1.00  24.07.14.
 */
@Path("workers")
public class WorkerController {
    @GET
    public InputStream getPage(@Context ServletContext context) {
        return context.getResourceAsStream("/WEB-INF/pages/workers.html");
    }

    @GET
    @Path("getWorkers{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserList getNextUsers(@Context HttpServletRequest request,
                                 @PathParam("page") int page) {
        UserList userList = new UserList(page);
        return userList;
    }

    @GET
    @Path("getWorkersByName{fullname}/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserList getNextUsers(@Context HttpServletRequest request,
                                 @PathParam("fullname") String fullName,
                                 @PathParam("page") int page) {
        return new UserList(fullName, page);
    }
}
