package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.Responses;
import com.bionic.socialNetwork.logic.UserAvatarLogic;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
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


    @GET
    @Path("delAvatar")
    @Produces(MediaType.APPLICATION_JSON)
    public String delAvatar(@Context HttpServletRequest request){
        long userId = (Long)request.getAttribute("userId");
        UserAvatarLogic userAvatarLogic = new UserAvatarLogic();
        userAvatarLogic.delAvatar(userId);
        return Responses.JSON_RESPONSE_TRUE;
    }
    @POST
    @Path("setAvatar")
    @Consumes(MediaType.MULTIPART_FORM_DATA)

    public String setAvatar(@FormDataParam("file") InputStream uploadedInputStream,
                            @FormDataParam("file") FormDataContentDisposition fileDetail,
                            @Context HttpServletRequest request,
                            @Context ServletContext context) {

        long userId = (Long) request.getAttribute("userId");
        String uploadedFileLocation = context.getRealPath("/WEB-INF");
        UserAvatarLogic userAvatarLogic = new UserAvatarLogic();
        userAvatarLogic.saveAvatar(uploadedInputStream, uploadedFileLocation, fileDetail.getFileName(), userId);
        return Responses.JSON_RESPONSE_TRUE;
    }
}
