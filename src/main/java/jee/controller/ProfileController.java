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

    @Path("/")
    @GET
    public Response getAllProfile() {
        return Response.ok(profileService.getAllProfile()).build();
    }


    @POST
    @Path("/")
    public Response createProfile(Profile profile) {
        return Response.ok(profileService.createProfile(profile)).build();
    }

    @Path("/{id}")
    @GET
    public Response findProfile(@PathParam("id") Long id) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        final var profile = profileService.findProfile(id);
        if (profile != null) {
            return Response.ok(profile).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @PUT
    public Response updateProfile(@PathParam("id") Long id, Profile newProfile) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        newProfile.setId(id);
        final var profile = profileService.updateProfile(newProfile);
        if (profile != null) {
            return Response.ok(profile).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deleteProfile(@PathParam("id") Long id) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        profileService.deleteProfile(id);
        return Response.noContent().build();
    }

    /** @Path("/{id}/publications")
     @GET public Response getPublicationProfile(@PathParam("id") Long id, Profile profile){
     if (id < 0) {
     return Response.status(Response.Status.BAD_REQUEST).build();
     }
     return Response.ok(publicationManager.getPublicationProfile(profile)).build();


     }**/


}
