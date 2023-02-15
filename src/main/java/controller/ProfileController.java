package controller;

import entity.Account;
import entity.Profile;
import service.ProfileManager;
import service.PublicationManager;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Stateless
@Path("/profiles")
@Produces({"application/json"})
@Consumes({"application/json"})

public class ProfileController {
    @Inject
    private ProfileManager profileManager;
    @Inject
    private PublicationManager publicationManager;

    @Path("/")
    @GET
    public Response getAllProfile() {
        return Response.ok(profileManager.getAllProfile()).build();
    }

    @POST
    @Path("/")
    public Response createProfile(Profile profile){
        return Response.ok(profileManager.createProfile(profile)).build();
    }

    @Path("/{id}")
    @GET
    public Response findProfile(@PathParam("id") Long id) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        final Profile profile = profileManager.findProfile(id);
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
        final Profile profile = profileManager.updateProfile(newProfile);
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
        profileManager.deleteProfile(id);
        return Response.noContent().build();
    }

    @Path("/{id}/publications")
    @GET
    public Response getPublicationProfile(@PathParam("id") Long id, Profile profile){
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(publicationManager.getPublicationProfile(profile)).build();


    }
}
