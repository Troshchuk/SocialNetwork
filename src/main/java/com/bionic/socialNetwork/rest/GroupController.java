package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.GroupLogic;
import com.bionic.socialNetwork.logic.lists.GroupPostList;
import com.bionic.socialNetwork.models.Group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  23.07.14.
 */
@Path("group{id}")
public class GroupController {
    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getPage(@Context HttpServletRequest request,
                               @PathParam("id") long id) {
        HttpSession session = request.getSession();
        return session.getServletContext()
                      .getResourceAsStream("/WEB-INF/pages/group.html");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("description")
    public Group getDescription(@PathParam("id") long id) {
        return new GroupLogic().getGroup(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("posts{page}")
    public GroupPostList getPosts(@PathParam("id") long id,
                                  @PathParam("page") int page) {
        return new GroupPostList(id, page);
    }
}
