package service;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/Register")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)

public class AccountCreateBean {
    @POST
    public String register(String formData){
        System.out.println("Registered successfully");
        return "Registered successfully";
    }
}
