package models;

import java.util.Objects;

public class Invoice {
    private int id;
    private int userId;
    private String name;
    private String email;
    private int price;
    private String noOfBedRooms;
    private int amount;
    private String latFrom;
    private String longFrom;
    private String latTo;
    private String longTo;
    private String date;


    public Invoice(int userId, String name, String email, int price, String noOfBedRooms, int amount, String latFrom, String longFrom, String latTo, String longTo, String date) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.price = price;
        this.noOfBedRooms = noOfBedRooms;
        this.amount = amount;
        this.latFrom = latFrom;
        this.longFrom = longFrom;
        this.latTo = latTo;
        this.longTo = longTo;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNoOfBedRooms() {
        return noOfBedRooms;
    }

    public void setNoOfBedRooms(String noOfBedRooms) {
        this.noOfBedRooms = noOfBedRooms;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getLatFrom() {
        return latFrom;
    }

    public void setLatFrom(String latFrom) {
        this.latFrom = latFrom;
    }

    public String getLongFrom() {
        return longFrom;
    }

    public void setLongFrom(String longFrom) {
        this.longFrom = longFrom;
    }

    public String getLatTo() {
        return latTo;
    }

    public void setLatTo(String latTo) {
        this.latTo = latTo;
    }

    public String getLongTo() {
        return longTo;
    }

    public void setLongTo(String longTo) {
        this.longTo = longTo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id && userId == invoice.userId && price == invoice.price && amount == invoice.amount && name.equals(invoice.name) && email.equals(invoice.email) && noOfBedRooms.equals(invoice.noOfBedRooms) && latFrom.equals(invoice.latFrom) && longFrom.equals(invoice.longFrom) && latTo.equals(invoice.latTo) && longTo.equals(invoice.longTo) && date.equals(invoice.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, email, price, noOfBedRooms, amount, latFrom, longFrom, latTo, longTo, date);
    }
}
