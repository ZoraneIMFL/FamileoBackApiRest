
import controller.AccountController;
import entity.Account;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.openejb.jee.WebApp;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.EnableServices;
import org.apache.openejb.testing.Module;
import org.junit.Test;
import org.junit.runner.RunWith;


import javax.ws.rs.core.Response;


import static org.junit.Assert.assertEquals;


@EnableServices(value = "jaxrs")
@RunWith(ApplicationComposer.class)
public class TestAccountController {

    private final Account account = new Account("DupontFamily", "dupont@gmail.com", "S*fdflip59", 0);


    @Module
    @Classes(AccountController.class)
    public WebApp app() {
        return new WebApp().contextRoot("test");


    }

    @Test
    public void testCreateAccount() throws Exception{
        WebClient client = WebClient.create("http://localhost:4204").path("/test/accounts");
        Response response = client.post(account);
        System.out.println(response.readEntity(String.class));

    }

}


