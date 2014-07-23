package com.bionic.socialNetwork.rest;


import com.bionic.socialNetwork.logic.SessionController;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Bish_UA on 22.07.2014.
 */

@Provider
public class SessionFilter implements ContainerRequestFilter {


    @Context
    private HttpServletRequest webRequest;


    @Override
    public ContainerRequest filter(ContainerRequest request) {

        SessionController sessionController = new SessionController();
        HttpSession httpSession = webRequest.getSession();
        long userID = sessionController.verifySession((String) httpSession.getAttribute("user"));

        System.out.println("session in filter: " + httpSession.getAttribute("user"));

        if (userID == -1) {
            try {
                if (!request.getRequestUri().equals(new URI("http://localhost:8080/sn/user/login")) &&
                        !request.getRequestUri().equals(new URI("http://localhost:8080/sn/user/registration"))) {

                    request.setUris(request.getBaseUri(), new URI("http://localhost:8080/sn/index"));

                }

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            webRequest.setAttribute("userID", userID);
        }
        return request;
    }

}