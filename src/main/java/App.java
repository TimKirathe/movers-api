import com.google.gson.Gson;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        Sql2oServiceDao sql2oServiceDao;
        Connection conn;
        Gson gson = new Gson();

//        String connectionString = "jdbc:postgresql://localhost:5432/movers_api";
//        Sql2o sql2o = new Sql2o(connectionString, "adamu", "Adamu");
        String connectionString = "jdbc:postgresql://ec2-44-195-191-252.compute-1.amazonaws.com:5432/d31d0ej81h1a3t";
        Sql2o sql2o = new Sql2o(connectionString, "lxrmrvoltijuwe", "24ddcf97e38426cc5908b6c83709d0b4294f3033c62a944542aa7f91571e8aaf");
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
