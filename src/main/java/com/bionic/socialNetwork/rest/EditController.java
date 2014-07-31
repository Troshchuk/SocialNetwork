package com.bionic.socialNetwork.rest;

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
 * @version 1.0 on 31.07.2014.
 * @autor Bish_UA
 */
@Path("edit")
public class EditController {
    @Context
    private ServletContext context;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getPage(@Context HttpServletRequest request){
        return context.getResourceAsStream("/WEB-INF/pages/useredit.html");
    }
}
