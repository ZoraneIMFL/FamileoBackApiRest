
package controller;
import entity.Account;
import service.AccountManager;
import service.AccountManagerBean;
import service.ProfileManager;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Stateless
@Path("/accounts")
@Produces({"application/json"})
@Consumes({"application/json"})

public class AccountController {

    @Inject
    private AccountManager accountManager;
    @Inject
    private ProfileManager profileManager;

    @POST
    @Path("/")
    public Response createAccount(Account account){
        return Response.ok(accountManager.createAccount(account)).build();
    }

    @Path("/")
    @GET
    public Response getAllAccount() {
        return Response.ok(accountManager.getAllAccount()).build();
    }

    @Path("/{id}")
    @GET
    public Response findAccount(@PathParam("id") Long id) {
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        final Account account = accountManager.findAccount(id);
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
        final Account account = accountManager.updateAccount(newAccount);
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
        accountManager.deleteAccount(id);
        return Response.noContent().build();
    }

    /// Pour chercher des profiles lié à un account !!
    @Path("/{id}/profiles")
    @GET
    public Response getProfileAccount(@PathParam("id") Long id,Account account){
        if (id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(profileManager.getProfileByAccount(account)).build();


    }

}


