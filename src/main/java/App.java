import com.google.gson.Gson;
import dao.Sql2oInvoiceDao;
import dao.Sql2oServiceDao;
import dao.Sql2oUserDao;
import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("public");

        Sql2oServiceDao sql2oServiceDao;
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        Sql2oUserDao sql2oUserDao;
        Sql2oInvoiceDao sql2oInvoiceDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://ec2-54-85-113-73.compute-1.amazonaws.com:5432/d1ngi3fj0iumcd";
        Sql2o sql2o = new Sql2o(connectionString, "lkyizybqkygkvk", "81e47c09943f1d05aabf6e71a744af70e738472850013f97193555842d8aaaa9");
        sql2oServiceDao = new Sql2oServiceDao(sql2o);
        sql2oUserDao = new Sql2oUserDao(sql2o);
        sql2oInvoiceDao = new Sql2oInvoiceDao(sql2o);
        conn = sql2o.open();

        // POST METHODS

        // Post Service to DB.
        post("/services/new", "application/json", (req, res) -> {
            Service service = gson.fromJson(req.body(), Service.class);
            sql2oServiceDao.save(service);
            res.status(201);
            PostServiceResponse postServiceResponse = new PostServiceResponse(201, "Successfully Added", service);
            return gson.toJson(postServiceResponse);
        });

        // Post User to DB.
        post("/users/new", "application/json", (req, res) -> {
            System.out.println(req.body().toString());
            User user = gson.fromJson(req.body(), User.class);
            String hashedPassword = user.getPassword();
            user.setPassword(hashedPassword);
            sql2oUserDao.save(user);
            res.status(201);
            PostUserResponse postUserResponse = new PostUserResponse(201, "Successfully Added", user);
            return gson.toJson(postUserResponse);
        });

        // User Logged In.
        post("/users/login", "application/json", (req, res) -> {
            LoginUser loginUser = gson.fromJson(req.body(), LoginUser.class);
            User user = sql2oUserDao.findByEmail(loginUser.getEmail());
            if (user == null) {
                res.status(404);
                ErrorResponse errorResponse = new ErrorResponse(404, "This user does not exist");
                return gson.toJson(errorResponse);
            }
            if (!loginUser.getPassword().equals(user.getPassword()) || !loginUser.getEmail().equals(user.getEmail())) {
                res.status(404);
                ErrorResponse errorResponse = new ErrorResponse(404, "The email or password is not correct.");
                return gson.toJson(errorResponse);
            }
            res.status(201);
            LoginResponse loginResponse = new LoginResponse(201, "Success", user);
            return gson.toJson(loginResponse);
        });

        // Get distance between two points.
        post("/calculate", "application/json", (req, res) -> {
            Calculate calculate = gson.fromJson(req.body(), Calculate.class);
            Service service = sql2oServiceDao.findById(calculate.getId());
            int rate = 1000;

            double distance = distanceCalculator.calculateDistance(
                    Double.parseDouble(calculate.getLongFrom()),Double.parseDouble(calculate.getLongTo()),Double.parseDouble(calculate.getLatFrom()),Double.parseDouble(calculate.getLatTo()));
            double price = service.getPrice() + (rate * distance );
            ResponseObject response = new ResponseObject(200, "Success", price);
            res.status(200);
            return gson.toJson(response);
        });

        // Post Invoice to DB.
        post("/invoice/new", "application/json", (req, res)-> {
            Invoice invoice = gson.fromJson(req.body(), Invoice.class);
            sql2oInvoiceDao.save(invoice);
            res.status(201);
            PostResponse postResponse = new PostResponse(201, "Successfully Added", invoice);
            return gson.toJson(postResponse);
        });



        // GET METHODS

        // Get all services.
        get("/services", "application/json", (req, res) -> {
            if (sql2oServiceDao.getAll().size() == 0) {
                ErrorResponse errorResponse = new ErrorResponse(404, "Sorry, there are no services currently available.");
                return gson.toJson(errorResponse);
            }
            ResponseList response = new ResponseList(200, "Success", sql2oServiceDao.getAll());
            res.status(200);
            return gson.toJson(response);
        });

        // Get service by ID.
        get("services/:id", "application/json", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            if (sql2oServiceDao.findById(id) == null) {
                ErrorResponse errorResponse = new ErrorResponse(404, "Sorry, no such service exists.");
                return gson.toJson(errorResponse);
            }
            ResponseObject responseObject = new ResponseObject(200, "Success", sql2oServiceDao.findById(id));
            res.status(200);
            return gson.toJson(responseObject);
        });

        // Get all invoices.
        get("invoice/all", "application/json", (req, res) -> {
            if (sql2oServiceDao.getAll().size() == 0) {
                ErrorResponse errorResponse = new ErrorResponse(404, "Sorry, there are no services currently available.");
                return gson.toJson(errorResponse);
            }
            ResponseList response = new ResponseList(200, "Success", sql2oServiceDao.getAll());
            res.status(200);
            return gson.toJson(response);
        });

        // Get all invoices for a specific user by user ID.
        get("invoice/user/:id", "application/json", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            if (sql2oInvoiceDao.findByUserId(id).size() == 0) {
                ErrorResponse errorResponse = new ErrorResponse(404, "You have no invoices.");
                return gson.toJson(errorResponse);
            }
            ResponseList responseList = new ResponseList(200, "Success", sql2oInvoiceDao.findByUserId(id));
            res.status(200);
            return gson.toJson(responseList);
        });

        // Get an invoice by its ID.
        get ("invoice/:id", "application/json", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            if (sql2oInvoiceDao.findById(id) == null) {
                ErrorResponse errorResponse = new ErrorResponse(404, "Sorry, no such invoice exists.");
                return gson.toJson(errorResponse);
            }
            ResponseObject responseObject = new ResponseObject(200, "Success", sql2oInvoiceDao.findById(id));
            res.status(200);
            return gson.toJson(responseObject);
        });

        after((req, res) -> {
            res.type("application/json");
        });
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
