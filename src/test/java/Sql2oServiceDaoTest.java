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
    void serviceReturnedSuccessfullyFromDB() {
        Service service1 = setUpService();
        Service service2 = sql2oServiceDao.findById(service1.getId());
        assertTrue(service1.equals(service2));
    }

    public Service setUpService() {
        Service service = new Service("Bed-Sitter", "https://i.postimg.cc/C1Z9YqcL/redd-sej-Ly-CD2-UQE-unsplash.jpg", "a one-roomed unit of accommodation typically consisting of combined bedroom and sitting room with cooking facilities.", 20000);
        service.setId(1);
        return service;
    }

}