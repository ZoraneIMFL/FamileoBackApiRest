package jee.controller;


import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jee.dto.LitePhoto;
import jee.model.Photo;
import jee.service.PhotoService;

import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("/photos")
@Produces({"application/json"})
@Consumes({"application/json"})
public class PhotoController {

    @Inject
    private PhotoService photoService;

    @POST
    @Path("/")
    public Response createPhoto(Photo photo) {
        return Response.ok(photoService.createPhoto(photo)).build();
    }

    @Path("/")
    @GET
    public Response getAllPhoto() {
        List<Photo> allPhoto = photoService.getAllPhoto();
        List<LitePhoto> allPhotoLite = new ArrayList<>();
        for(var i = 0; i < allPhoto.size(); i++) {
            allPhotoLite.add(new LitePhoto(allPhoto.get(i)));
        }
        return Response.ok(allPhotoLite).build();
    }

    @Path("/{id}")
    @PUT
    public Response updatePhoto(@PathParam("id") Long id, Photo newPhoto) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        newPhoto.setId(id);
        final var publication = photoService.updatePhoto(newPhoto);
        if (publication != null) {
            return Response.ok(publication).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deletePhoto(@PathParam("id") Long id) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        photoService.deletePhoto(id);
        return Response.noContent().build();
    }

    @Path("/{id}")
    @GET
    public Response findPhoto(@PathParam("id") Long id) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        final var photo = photoService.findPhoto(id);
        if (photo != null) {
            return Response.ok(photo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
