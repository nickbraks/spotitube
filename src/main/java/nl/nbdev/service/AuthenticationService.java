package nl.nbdev.service;

import nl.nbdev.dto.TokenDTO;
import nl.nbdev.dto.UserDTO;
import nl.nbdev.persistence.TokenDAO;
import nl.nbdev.persistence.UserDAO;

public class AuthenticationService {
    private UserDAO userDAO = new UserDAO();
    private TokenDAO tokenDAO = new TokenDAO();

    public TokenDTO login(String username, String password) {
        UserDTO authenticatedUser = userDAO.getUser(username, password);

        if (authenticatedUser != null) {
            return tokenDAO.getTokenByUser(authenticatedUser.getUser());
        } else {
            throw new SpotitubeLoginException("Login failed for user " + username);
        }

    }
}
