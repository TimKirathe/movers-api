package models;

public class PostServiceResponse {

    private int status;
    private String message;
    private Service service;

    public PostServiceResponse(int status, String message, Service service) {
        this.status = status;
        this.message = message;
        this.service = service;
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
