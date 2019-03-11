package nl.nbdev.dto;

public class UserDTO {
    private String user;
    private String password;

    public UserDTO() {

    }

    public UserDTO(String user, String password) {
        this.user = user;
        this.password = password;
    }


    public String getUsername() {
        return user;
    }

    public void setUsername(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
