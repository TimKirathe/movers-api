package dao;

import models.Booking;
import models.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oBookingDaoTest {
    private static Sql2oBookingDao sql2oBookingDao;
    private static Sql2oUserDao sql2oUserDao;
    private static Connection conn;

    @BeforeAll
    static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/movers_api_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        sql2oBookingDao = new Sql2oBookingDao(sql2o);
        sql2oUserDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void afterEach() {
        sql2oUserDao.clearAll();
        sql2oBookingDao.clearAll();
    }

    @AfterAll
    static void tearDown() {
        conn.close();
    }

    @Test
    void bookingSavedToDBSuccessfully() {
        User user = setUpUser();
        sql2oUserDao.save(user);
        Booking booking = setUpBooking();
        sql2oBookingDao.save(booking);
        List<Booking> databaseBookings = sql2oBookingDao.findAllBookingsByUserId(user.getId());
        assertTrue(databaseBookings.get(0).equals(booking));
    }

    public User setUpUser() {
        User user = new User("34kjdfhasdh45kjsdfj", "Timothy", "t@gmail.com", "1234567");
        return user;
    }

    public Booking setUpBooking() {
        Booking booking = new Booking("21", "-21", "43", "-43", "21/04/2022", 1);
        return booking;
    }

}