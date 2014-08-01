package com.bionic.socialNetwork.rest;

import com.bionic.socialNetwork.logic.PrivateMessageLogic;
import com.bionic.socialNetwork.logic.lists.ReceivedMessageList;
import com.bionic.socialNetwork.logic.lists.SentMessageList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  25.07.14.
 */

@Path("pm")
public class PrivateMessageController {
    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getPage(@Context ServletContext context) {
        return context.getResourceAsStream("/WEB-INF/pages/pm.html");
    }

    @GET
    @Path("received{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public ReceivedMessageList getReceivedMessages(
            @Context HttpServletRequest request,
            @PathParam("page") int page) {
        return new ReceivedMessageList((Long) request.getAttribute("userId"),
                                       page);
    }

    @GET
    @Path("sent{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public SentMessageList getSentMessage(@Context HttpServletRequest request,
                                          @PathParam("page") int page) {
        return new SentMessageList((Long) request.getAttribute("userId"), page);
    }

    @POST
    @Path("sendMessage")
    @Produces(MediaType.APPLICATION_JSON)
    public String sendMessage(@Context HttpServletRequest request,
                              @FormParam("to") long toUserId,
                              @FormParam("msg") String msg) {
        return "{\"result\": " + new PrivateMessageLogic()
                .createPm((Long) request.getAttribute("userId"), toUserId, msg);
    }

    @GET
    @Path("getMessage{msgId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMessage(@Context HttpServletRequest request,
                             @PathParam("msgId") long msgId) {
        return "{\"message\": \"" + new PrivateMessageLogic()
                .readPm((Long) request.getAttribute("userId"), msgId) + "\"}";
    }
}
