package dao;

import dao.Sql2oServiceDao;
import models.Service;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.*;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oServiceDaoTest {
    private static Sql2oServiceDao sql2oServiceDao;
    private static Connection conn;

    @BeforeAll
    static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/movers_api_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        sql2oServiceDao = new Sql2oServiceDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void afterEach() {
        sql2oServiceDao.clearAll();
    }

    @AfterAll
    static void tearDown() {
        conn.close();
    }

    @Test
    void serviceSavedToTheDBandReturnedCorrectly() {
        Service service1 = setUpService();
        sql2oServiceDao.save(service1);
        Service service2 = sql2oServiceDao.getAll().get(0);
        assertTrue(service1.equals(service2));
    }

    @Test
    void serviceSuccessfullyDeletedFromDB() {
        Service service1 = setUpService();
        sql2oServiceDao.save(service1);
        sql2oServiceDao.deleteById(service1.getId());
        assertEquals(0, sql2oServiceDao.getAll().size());
    }

    public Service setUpService() {
        Service service = new Service("1", "https://i.postimg.cc/C1Z9YqcL/redd-sej-Ly-CD2-UQE-unsplash.jpg", 20000);
        return service;
    }

}