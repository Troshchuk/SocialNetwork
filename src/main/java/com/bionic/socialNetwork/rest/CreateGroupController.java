package com.bionic.socialNetwork.rest;


import com.bionic.socialNetwork.logic.GroupLogic;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.logic.CreateGroupLogic;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

/**
 * rest for CreateGroup
 * @author Matvey Mitnitskyi
 * @version 1.00 28.07.2014.
 */

//not finished
@Path("newGroup")
public class CreateGroupController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public String confirmCreating(@FormParam("name") String name,
                                  @FormParam("description") String description,
                                  @Context HttpServletRequest request) {
        long userId = (Long)request.getAttribute("userId");
        CreateGroupLogic createGroup =
                new CreateGroupLogic(name, description, userId);
        new GroupLogic().follow(userId, createGroup.getGroup().getGroupId());
        return createGroup.getResponse();
    }

}
