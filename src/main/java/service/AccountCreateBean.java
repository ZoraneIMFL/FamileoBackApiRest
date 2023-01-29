package service;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@Path("/Register")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)

public class AccountCreateBean {
    private static final Logger LOGGER = Logger.getLogger(AccountCreateBean.class.getName());
    @POST
    public String register(String formData){
        LOGGER.log(Level.FINE, "Registered successfully");
        return "Registered successfully";
    }
}
