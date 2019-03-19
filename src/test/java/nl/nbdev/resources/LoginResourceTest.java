package nl.nbdev.resources;

import nl.nbdev.dto.TokenDTO;
import nl.nbdev.dto.UserDTO;
import nl.nbdev.persistence.UserDAO;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LoginResourceTest {

    @Mock // Dit object moet gemmocked worden
    private UserDAO userDAOStub;

    @InjectMocks // Het object waar de mock gebruikt wordt
    private LoginResource sut;

    @Test
    public void loginSucces() {
        UserDTO mockedUser = new UserDTO();
        mockedUser.setUser("testuser");
        mockedUser.setPassword("testpassword");
        Mockito.when(userDAOStub.getUser("testuser", "testpassword")).thenReturn(mockedUser);

        UserDTO userDTO = new UserDTO("testuser", "testpassword");
        Response actualResult = sut.postLoginCredentials(userDTO);

        TokenDTO actualToken = (TokenDTO) actualResult.getEntity();

        assertEquals("testuser", actualToken.getToken());
        assertEquals("123-123-123", actualToken.getToken());
    }

}