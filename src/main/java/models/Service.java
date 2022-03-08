package models;

import java.util.Objects;

public class Service {

    private int id;
    private String noOfBedRooms;
    private String photoLink;
    private int price;

    public Service(String noOfBedRooms, String photoLink, int price) {
        this.noOfBedRooms = noOfBedRooms;
        this.photoLink = photoLink;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public String getNoOfBedRooms() {
        return noOfBedRooms;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNoOfBedRooms(String noOfBedRooms) {
        this.noOfBedRooms = noOfBedRooms;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return id == service.id && price == service.price && noOfBedRooms.equals(service.noOfBedRooms) && photoLink.equals(service.photoLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, noOfBedRooms, photoLink, price);
    }
}
