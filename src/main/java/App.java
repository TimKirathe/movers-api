import com.google.gson.Gson;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {

        Sql2oServiceDao sql2oServiceDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/movers_api";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        sql2oServiceDao = new Sql2oServiceDao(sql2o);
        conn = sql2o.open();

        get("/services", "application/json", (req, res) -> {
            if (sql2oServiceDao.getAll().size() == 0) {
                String noData = "Sorry, there are currently no services available";
                return gson.toJson(noData);
            }
            return gson.toJson(sql2oServiceDao.getAll());
        });

        after((req, res) -> {
            res.type("application/json");
        });
    }
}
