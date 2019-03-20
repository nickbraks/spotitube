package nl.nbdev.resources;

import nl.nbdev.dto.ErrorDTO;
import nl.nbdev.dto.TokenDTO;
import nl.nbdev.dto.UserDTO;
import nl.nbdev.persistence.TokenDAO;
import nl.nbdev.persistence.UserDAO;
import nl.nbdev.service.AuthenticationService;

import javax.security.auth.login.LoginException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/login")
public class LoginResource {

    private AuthenticationService authenticationService = new AuthenticationService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userCredentials) {
        TokenDTO token = authenticationService.login(userCredentials.getUser(), userCredentials.getPassword());
        return Response.ok(token).build();
    }
}
