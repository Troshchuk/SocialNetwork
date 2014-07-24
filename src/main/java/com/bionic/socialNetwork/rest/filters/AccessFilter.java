package com.bionic.socialNetwork.rest.filters;


import com.bionic.socialNetwork.logic.SessionController;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by Bish_UA on 22.07.2014.
 */

@Provider
public class AccessFilter implements ContainerRequestFilter {


    @Context
    private HttpServletRequest webRequest;
    @Context
    HttpHeaders httpHeaders;
    Long userId;

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        Map cookies = httpHeaders.getCookies();
        Cookie cookie = (Cookie) cookies.get("sessionId");
        SessionController sessionController = new SessionController();

        if (cookie != null) {
            userId = sessionController.verifySession(cookie.getValue());
            webRequest.setAttribute("userId", userId);
        } else {
            try {
                URI uri = request.getRequestUri();
                if (!request.getRequestUri().equals(new URI(
                        "http://" + uri.getHost() + ":" + uri.getPort() +
                        "/sn/user/login")) &&
                    !request.getRequestUri().equals(new URI(
                            "http://" + uri.getHost() + ":" + uri.getPort() +
                            "/sn/user/registration"))) {
                    request.setUris(request.getBaseUri(),
                                    new URI("http://" + uri.getHost() + ":" +
                                            uri.getPort() + "/sn/index"));
                }

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

        }


        return request;


    }

}