package nl.nbdev.resources;


import nl.nbdev.dto.ErrorDTO;
import nl.nbdev.dto.PlaylistDTO;
import nl.nbdev.dto.PlaylistsDTO;
import nl.nbdev.dto.TracksDTO;
import nl.nbdev.persistence.PlaylistsDAO;
import nl.nbdev.persistence.TokenDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists/")
public class PlaylistsResource {

    private PlaylistsDAO playlistsDAO = new PlaylistsDAO();
    private TokenDAO tokenDAO = new TokenDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        if (tokenDAO.ValidateToken(token)) {
            PlaylistsDTO lists = new PlaylistsDTO();
            lists.setPlaylists(playlistsDAO.getPlaylistsFromUser());
            lists.setLength(playlistsDAO.getLengthOfPlaylist());
            return Response.ok(lists).build();
        } else {
            ErrorDTO forbidden = new ErrorDTO(403, "403, Forbidden.");
            return Response.status(forbidden.getCode()).entity(forbidden).build();
        }
    }


    @Path("{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        if (tokenDAO.ValidateToken(token)) {
            return Response.ok(
                    new TracksDTO(
                            playlistsDAO.getTracksFromPlaylist(id)
                    )
            ).build();
        } else {
            ErrorDTO forbidden = new ErrorDTO(403, "403, Forbidden.");
            return Response.status(forbidden.getCode()).entity(forbidden).build();
        }

    }
}
