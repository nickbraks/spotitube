package nl.nbdev.persistence;

import nl.nbdev.dto.PlaylistDTO;
import nl.nbdev.dto.TrackDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistsDAO {

    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public List<PlaylistDTO> getPlaylistsFromUser(String token) {
        List<PlaylistDTO> playlists = new ArrayList<>();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT playlistID, name, IF(" +
                        "(SELECT token FROM token WHERE username = p.createdBy) = ?, true, false) AS owner, createdBy " +
                        "FROM playlist p")
        ) {
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                playlists.add(
                        new PlaylistDTO(
                                resultSet.getInt("playlistID"),
                                resultSet.getString("name"),
                                resultSet.getBoolean("owner")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlists;
    }

    public List<TrackDTO> getTracksFromPlaylist(int playlistID) {
        List<TrackDTO> trackList = new ArrayList<>();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT track.trackID, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable " +
                        "FROM track RIGHT OUTER JOIN trackinplaylist ON track.trackID = trackinplaylist.trackID " +
                        "WHERE trackinplaylist.playlistID = ?")
        ) {
            preparedStatement.setInt(1, playlistID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TrackDTO trackDTO = new TrackDTO();

                trackDTO.setId(resultSet.getInt("trackID"));
                trackDTO.setTitle(resultSet.getString("title"));
                trackDTO.setPerformer(resultSet.getString("performer"));
                trackDTO.setDuration(resultSet.getInt("duration"));
                trackDTO.setAlbum(resultSet.getString("album"));
                trackDTO.setPlaycount(resultSet.getInt("playcount"));
                trackDTO.setPublicationDate(resultSet.getString("publicationDate"));
                trackDTO.setDescription(resultSet.getString("description"));
                trackDTO.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));

                trackList.add(trackDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trackList;
    }

    public int getLengthOfPlaylist() {
        int playlistLength = 0;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(duration) AS totalDuration " +
                        "FROM track INNER JOIN trackinplaylist " +
                        "ON track.trackID = trackinplaylist.trackID")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                playlistLength = resultSet.getInt("totalDuration");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlistLength;
    }
}
