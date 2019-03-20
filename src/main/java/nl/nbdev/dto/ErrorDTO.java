package nl.nbdev.dto;

public class ErrorDTO {
    private int code;
    private String message;

    public ErrorDTO() {

    }

    public ErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
