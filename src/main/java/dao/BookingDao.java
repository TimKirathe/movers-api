package dao;

import models.Booking;

import java.util.List;

public interface BookingDao {

    // CREATE
    void save(Booking booking);

    // READ
    Booking findById(int id);
    List<Booking> findAllBookingsByUserId(int userId);

    // UPDATE


    // DELETE
    void clearAll();
    void deleteById(int id);
}
