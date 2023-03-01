package jee.controller;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jee.dto.LitePublication;
import jee.model.Publication;
import jee.service.PublicationService;

import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("/publications")
@Produces({"application/json"})
@Consumes({"application/json"})
public class PublicationController {
    @Inject
    private PublicationService publicationService;

    @POST
    @Path("/")
    public Response createPublication(Publication publication) {
        return Response.ok(publicationService.createPublication(publication)).build();
    }

    @Path("/")
    @GET
    public Response getAllPublication() {
        var allPublication = publicationService.getAllPublication();
        var allLitePublications = new ArrayList<>();
        for(int i = 0; i < allPublication.size(); i++) {
            allLitePublications.add(new LitePublication(allPublication.get(i)));
        }
        return Response.ok(allLitePublications).build();
    }

    @Path("/{id}")
    @GET
    public Response findPublication(@PathParam("id") Long id) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        final var publication = publicationService.findPublication(id);
        if (publication != null) {
            return Response.ok(publication).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @PUT
    public Response updatePublication(@PathParam("id") Long id, Publication newPublication) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        newPublication.setId(id);
        final var publication = publicationService.updatePublication(newPublication);
        if (publication != null) {
            return Response.ok(publication).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deletePublication(@PathParam("id") Long id) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        publicationService.deletePublication(id);
        return Response.noContent().build();
    }

}
