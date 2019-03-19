package nl.nbdev.resources;

import nl.nbdev.dto.ErrorDTO;
import nl.nbdev.dto.TokenDTO;
import nl.nbdev.dto.UserDTO;
import nl.nbdev.persistence.TokenDAO;
import nl.nbdev.persistence.UserDAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/login")
public class LoginResource {

    private UserDAO userDAO = new UserDAO();
    private TokenDAO tokenDAO = new TokenDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postLoginCredentials(UserDTO userCredentials) {
        UserDTO authenticatedUser = userDAO.getUser(userCredentials.getUser(), userCredentials.getPassword());
        if (authenticatedUser != null) {
            return Response.ok(tokenDAO.getTokenByUser(authenticatedUser.getUser())).build();
        } else {
            ErrorDTO unauthorized = new ErrorDTO(401, "401, Authorization has failed for user: " + userCredentials.getUser());
            return Response.status(unauthorized.getCode()).entity(unauthorized).build();
        }
    }
}
