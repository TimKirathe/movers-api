package dao;

import models.Booking;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM bookings WHERE id = :id";
            return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Booking.class);
        } catch (Sql2oException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Booking> findAllBookingsByUserId(int userId) {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT bookings.id, latfrom, longfrom, latto, longto, date, bookings.userid FROM bookings INNER JOIN users ON bookings.userid = users.id WHERE bookings.userid = :userId";
            return con.createQuery(sql).addParameter("userId", userId).executeAndFetch(Booking.class);
        } catch (Sql2oException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public void clearAll() {
        String sql1 = "TRUNCATE TABLE bookings";
        String sql2 = "ALTER SEQUENCE bookings_id_seq RESTART";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql1).executeUpdate();
            con.createQuery(sql2).executeUpdate();
        } catch (Sql2oException e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try(Connection con = sql2o.open()) {
            String sql = "DELETE FROM bookings WHERE id = :id";
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        } catch (Sql2oException e) {
            System.out.println(e);
        }
    }
}
