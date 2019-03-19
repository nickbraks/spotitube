package nl.nbdev.persistence;

import nl.nbdev.dto.PlaylistDTO;
import nl.nbdev.dto.TokenDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TokenDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public TokenDTO getTokenByUser(String username) {
        TokenDTO foundToken = null;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT token, account.name " +
                        "FROM token INNER JOIN account " +
                        "ON token.username = account.username " +
                        "WHERE token.username = ?")
        ) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                foundToken = new TokenDTO(resultSet.getString("token"), resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundToken;
    }

    public boolean ValidateToken(String token) {
        boolean foundToken = false;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT token, account.name " +
                        "FROM token INNER JOIN account " +
                        "ON token.username = account.username " +
                        "WHERE token.token = ?")
        ) {
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                foundToken = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundToken;
    }

    /*public void generateUUID() {
        UUID.randomUUID().toString();
    }*/
}
