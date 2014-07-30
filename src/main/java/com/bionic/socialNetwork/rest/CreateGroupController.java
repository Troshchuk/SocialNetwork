package com.bionic.socialNetwork.rest;


import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.logic.CreateGroupLogic;
import javax.servlet.ServletContext;
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
@Path("user{id}/newGroup")
public class CreateGroupController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public InputStream createGroup(@Context ServletContext context) {

        return context.getResourceAsStream("/Web-INF/pages/newGroup.html");

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    @Path("confirmCreating")
    public String confirmCreating(@FormParam("name") String name,
                                  @FormParam("description") String description,
                                  @PathParam("id") long id) {

        CreateGroupLogic createGroup =
                new CreateGroupLogic(name, description, id);
        return createGroup.getResponse();
    }

}
