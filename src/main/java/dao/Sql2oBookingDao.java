package dao;

import models.Booking;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oBookingDao implements BookingDao {

    private final Sql2o sql2o;

    public Sql2oBookingDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(Booking booking) {
        try(Connection con = sql2o.open()) {
            String sql = "INSERT INTO bookings (latfrom, longfrom, latto, longto, date, userid) VALUES (:latFrom, :longFrom, :latTo, :longTo, :date, :userId)";
            int id = (int) con.createQuery(sql).bind(booking)
                    .addParameter("latFrom", booking.getLatFrom())
                    .addParameter("longFrom", booking.getLongFrom())
                    .addParameter("latTo", booking.getLatTo())
                    .addParameter("longTo", booking.getLongTo())
                    .addParameter("date", booking.getDate())
                    .addParameter("userId", booking.getUserId())
                    .executeUpdate().getKey();
            booking.setId(id);
        }
    }

    @Override
    public Booking findById(int id) {
        return null;
    }

    @Override
    public List<Booking> findAllBookingsByUserId(int id) {
        return null;
    }

    @Override
    public void clearAll() {

    }

    @Override
    public void deleteById(int id) {

    }
}
