package nl.nbdev.dto;

import java.util.List;

public class PlaylistDTO {
    private int id;
    private String name;
    private Boolean owner;
    private int[] tracks;

    public PlaylistDTO() {
    }

    public PlaylistDTO(int id, String name, Boolean owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = new int[0];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

    public int[] getTracks() {
        return tracks;
    }

    public void setTracks(int[] tracks) {
        this.tracks = tracks;
    }
}
