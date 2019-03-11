package nl.nbdev.resources;

import nl.nbdev.ErrorMessages;
import nl.nbdev.dto.TokenDTO;
import nl.nbdev.dto.UserDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postLoginCredentials(UserDTO userCredentials) {
        UserDTO user = new UserDTO("nick", "test");
        TokenDTO token = new TokenDTO("123", user.getUsername());
        if (user.getUsername().equals(userCredentials.getUsername()) && user.getPassword().equals(userCredentials.getPassword())) {
            return Response.ok(token).build();
        } else {
            ErrorMessages unauthorized = new ErrorMessages(401, "401, Authorization has failed.");
            return Response.status(unauthorized.getCode()).entity(unauthorized).build();
        }
    }
}
