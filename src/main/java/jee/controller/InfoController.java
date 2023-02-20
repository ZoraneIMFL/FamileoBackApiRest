package jee.controller;

import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Stateless
@Path("/info")
@Produces({"application/json"})
@Consumes({"application/json"})
public class InfoController {

    @Path("/ip")
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public Response getIp(@Context HttpServletRequest request) {
        return Response.ok(request.getRemoteAddr()).build();
    }
}
