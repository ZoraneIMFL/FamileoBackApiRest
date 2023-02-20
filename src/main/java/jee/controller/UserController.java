package jee.controller;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jee.model.User;
import jee.service.UserService;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Stateless
@Path("/users")
@Produces({"application/json"})
@Consumes({"application/json"})
public class UserController {

    @Inject
    private UserService userService;


    @Path("/")
    @GET
    public Response getAllUser() {
        return Response.ok(userService.getAllUser()).build();
    }

    @Path("/{id}")
    @GET
    public Response findUser(@PathParam("id") Long id) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        final User user = userService.findUser(id);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @PUT
    public Response updateUser(@PathParam("id") Long id, User newUser) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        newUser.setId(id);
        final User user = userService.updateUser(newUser);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deleteUser(@PathParam("id") Long id) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        userService.deleteUser(id);
        return Response.noContent().build();
    }

    @Path("/")
    @POST
    public Response createUser(User user) {
        return Response.ok(userService.createUser(user)).build();
    }
}
