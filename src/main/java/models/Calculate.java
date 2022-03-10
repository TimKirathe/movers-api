package models;

import java.util.Objects;

public class Calculate {
    private int id;
    private String latFrom;
    private String longFrom;
    private String latTo;
    private String longTo;

    public Calculate(String latFrom, String longFrom, String latTo, String longTo) {
        this.latFrom = latFrom;
        this.longFrom = longFrom;
        this.latTo = latTo;
        this.longTo = longTo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calculate calculate = (Calculate) o;
        return id == calculate.id && latFrom.equals(calculate.latFrom) && longFrom.equals(calculate.longFrom) && latTo.equals(calculate.latTo) && longTo.equals(calculate.longTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latFrom, longFrom, latTo, longTo);
    }
}
