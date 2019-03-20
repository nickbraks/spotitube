package nl.nbdev.exceptionmapper;

import nl.nbdev.dto.ErrorDTO;
import nl.nbdev.service.SpotitubeLoginException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LoginExceptionMapper implements ExceptionMapper<SpotitubeLoginException> {

    @Override
    public Response toResponse(SpotitubeLoginException message) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorDTO(message.getMessage()))
                .build();
    }
}
