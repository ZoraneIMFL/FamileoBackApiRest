package jee.controller;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jee.model.Profile;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import jee.service.ProfileService;

@Stateless
@Path("/profiles")
@Produces({"application/json"})
@Consumes({"application/json"})
public class ProfileController {

    @Inject
    private ProfileService profileService;

    @POST
    @Path("/")
    public Response createProfile(Profile profile){
        return Response.ok(profileService.createProfile(profile)).build();
    }

}
