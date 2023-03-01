package jee.controller;

import jee.model.Account;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jee.model.Profile;
import jee.service.AccountService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jee.service.ProfileService;

import java.util.List;

@Stateless
@Path("/accounts")
@Produces({"application/json"})
@Consumes({"application/json"})
public class AccountController {

    @Inject
    private AccountService accountService;
    @Inject
    private ProfileService profileService;


    @POST
    @Path("/")
    public Response createAccount(Account account) {
        return Response.ok(accountService.createAccount(account)).build();
    }

    @Path("/")
    @GET
    public Response getAllAccount() {
        return Response.ok(accountService.getAllAccount()).build();
    }

    @Path("/{id}")
    @GET
    public Response findAccount(@PathParam("id") Long id) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        final var account = accountService.findAccount(id);
        if (account != null) {
            return Response.ok(account).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @PUT
    public Response updateAccount(@PathParam("id") Long id, Account newAccount) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        newAccount.setId(id);
        final var account = accountService.updateAccount(newAccount);
        if (account != null) {
            return Response.ok(account).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deleteAccount(@PathParam("id") Long id) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        accountService.deleteAccount(id);
        return Response.noContent().build();
    }

    @Path("/{id}/profiles")
    @GET
    public Response findProfilesByAccountId(@PathParam("id") Long id) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        var account = accountService.findAccount(id);
        if (account == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Profile> profiles = profileService.getAllProfileOfAccount(id);
        return Response.ok(profiles).build();
    }


}