package models;

import java.util.Objects;

public class LoginResponse {

    private int status;
    private String message;
    private User user;

    public LoginResponse(int status, String message, User user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginResponse response = (LoginResponse) o;
        return status == response.status && message.equals(response.message) && user.equals(response.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, user);
    }
}
