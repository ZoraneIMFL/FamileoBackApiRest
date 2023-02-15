package controller;

import entity.Account;
import entity.Publication;
import service.PublicationManager;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Stateless
@Path("/publications")
@Produces({"application/json"})
@Consumes({"application/json"})
public class PublicationController {
    @Inject
    private PublicationManager publicationManager;

    @POST
    @Path("/")
    public Response createPublication(Publication publication){
        return Response.ok(publicationManager.createPublication(publication)).build();
    }

    @Path("/")
    @GET
    public Response getAllPublication() {
        return Response.ok(publicationManager.getAllPublication()).build();
    }

    @Path("/{id}")
    @GET
    public Response findAccount(@PathParam("id") Long id) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        final Publication publication = publicationManager.findPublication(id);
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
        final Publication publication = publicationManager.updatePublication(newPublication);
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
        publicationManager.deletePublication(id);
        return Response.noContent().build();
    }
}
