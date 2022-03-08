import com.google.gson.Gson;
import dao.Sql2oServiceDao;
import dao.Sql2oUserDao;
import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {

        Sql2oServiceDao sql2oServiceDao;
        Sql2oUserDao sql2oUserDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/movers_api";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        sql2oServiceDao = new Sql2oServiceDao(sql2o);
        sql2oUserDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();

        // POST METHODS

        // Post Service to DB
        post("/services/new", "application/json", (req, res) -> {
            Service service = gson.fromJson(req.body(), Service.class);
            sql2oServiceDao.save(service);
            res.status(201);
            PostResponse postResponse = new PostResponse(201, "Successfully Added");
            return gson.toJson(postResponse);
        });

        post("/users/new", "application/json", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            sql2oUserDao.save(user);
            res.status(201);
            PostResponse postResponse = new PostResponse(201, "Successfully Added");
            return gson.toJson(postResponse);
        });


        // GET METHODS

        // Get all services
        get("/services", "application/json", (req, res) -> {
            if (sql2oServiceDao.getAll().size() == 0) {
                String noData = "Sorry, there are currently no services available";
                return gson.toJson(noData);
            }
            ResponseList response = new ResponseList(200, "Success", sql2oServiceDao.getAll());
            res.status(200);
            return gson.toJson(response);
        });

        // Get service by ID.
        get("services/:id", "application/json", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            if (sql2oServiceDao.findById(id) == null) {
                String noData = "Sorry no such service exists";
                return gson.toJson(noData);
            }
            ResponseObject responseObject = new ResponseObject(200, "Success", sql2oServiceDao.findById(id));
            res.status(200);
            return gson.toJson(responseObject);
        });

        get("users/:userId", "application/json", (req, res) -> {
            String userId = req.params("userId");
            if (sql2oUserDao.findById(userId) == null) {
                ErrorResponse response = new ErrorResponse(404, "That user does not exist.");
                res.status(404);
                return gson.toJson(response);
            }
            ResponseObject responseObject = new ResponseObject(200, "Success", sql2oUserDao.findById(userId));
            res.status(200);
            return gson.toJson(responseObject);
        });


        after((req, res) -> {
            res.type("application/json");
        });
    }
}
