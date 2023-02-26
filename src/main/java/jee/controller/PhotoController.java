package jee.controller;


import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jee.model.Account;
import jee.model.Photo;
import jee.service.AccountService;
import jee.service.PhotoService;

@Stateless
@Path("/photos")
@Produces({"application/json"})
@Consumes({"application/json"})
public class PhotoController {

    @Inject
    private PhotoService photoService;

    @POST
    @Path("/")
    public Response createAccount(Photo photo){
        return Response.ok(photoService.createPhoto(photo)).build();
    }

    @Path("/")
    @GET
    public Response getAllAccount() {
        return Response.ok(photoService.getAllPhoto()).build();
    }
}
