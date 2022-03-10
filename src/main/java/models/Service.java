package models;

import java.util.Objects;

public class Service {

    private int id;
    private String name;
    private String noOfBedRooms;
    private String photoLink;
    private int price;

    public Service(String name, String noOfBedRooms, String photoLink, int price) {
        this.name = name;
        this.noOfBedRooms = noOfBedRooms;
        this.photoLink = photoLink;
        this.price = price;
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

    public String getNoOfBedRooms() {
        return noOfBedRooms;
    }

    public void setNoOfBedRooms(String noOfBedRooms) {
        this.noOfBedRooms = noOfBedRooms;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return id == service.id && price == service.price && Objects.equals(name, service.name) && Objects.equals(noOfBedRooms, service.noOfBedRooms) && Objects.equals(photoLink, service.photoLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, noOfBedRooms, photoLink, price);
    }
}
