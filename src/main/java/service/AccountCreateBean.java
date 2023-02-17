package service;
import dao.AccountDao;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@Path("/Register")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)

public class AccountCreateBean {

    @EJB
    private AccountDao dao;
    
    private static final Logger LOGGER = Logger.getLogger(AccountCreateBean.class.getName());
    @POST
    public String register(String formData){
        LOGGER.log(Level.FINE, "Registered successfully");
        return "Registered successfully";
    }
}
