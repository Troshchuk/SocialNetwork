package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.lists.GroupList;

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
 * @version 1.00  01.08.14.
 */
@Path("groups")
public class GroupsController {
    @Context
    private ServletContext context;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getPage(@Context HttpServletRequest request,
                               @PathParam("id") long id) {
        return context.getResourceAsStream("/WEB-INF/pages/groups.html");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{page}")
    public GroupList getGroups(@Context HttpServletRequest request,
                               @PathParam("page") int page) {
        return new GroupList((Long) request.getAttribute("userId"), page);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("search{groupName}/{page}")
    public GroupList search(@PathParam("groupName") String groupName,
                            @PathParam("page") int page) {
        return new GroupList(groupName, page);
    }
}
