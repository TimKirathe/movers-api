package models;

import java.sql.Timestamp;
import java.util.Objects;

public class Booking {

    private int id;
    private String latFrom;
    private String longFrom;
    private String latTo;
    private String longTo;
    private String date;
    private int userId;

    public Booking(String latFrom, String longFrom, String latTo, String longTo, String date, int userId) {
        this.latFrom = latFrom;
        this.longFrom = longFrom;
        this.latTo = latTo;
        this.longTo = longTo;
        this.date = date;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id && userId == booking.userId && latFrom.equals(booking.latFrom) && longFrom.equals(booking.longFrom) && latTo.equals(booking.latTo) && longTo.equals(booking.longTo) && date.equals(booking.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latFrom, longFrom, latTo, longTo, date, userId);
    }
}
