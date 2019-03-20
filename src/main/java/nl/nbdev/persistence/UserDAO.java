package nl.nbdev.persistence;

import nl.nbdev.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public UserDTO getUser(String username, String password) {
        UserDTO foundUser = null;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account " +
                        "WHERE username = ? AND password = ?")
        ) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundUser = new UserDTO();
                foundUser.setUser(username);
                foundUser.setPassword(password);

            }
        } catch (SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
        return foundUser;
    }
}
