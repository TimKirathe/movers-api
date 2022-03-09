package dao;

import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oUserDao implements UserDao {

    private final Sql2o sql2o;

    public Sql2oUserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(User user) {
        try(Connection con = sql2o.open()) {
            String sql = "INSERT INTO users (name, userid, email, password) VALUES (:name, :userId, :email, :password)";
            int id = (int) con.createQuery(sql).bind(user)
                    .addParameter("name", user.getName())
                    .addParameter("userId", user.getUserId())
                    .addParameter("email", user.getEmail())
                    .addParameter("password", user.getPassword())
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException e) {
            System.out.println(e);
        }
    }

    @Override
    public User findByEmail(String email) {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM users WHERE email = :email";
            return con.createQuery(sql).addParameter("email", email).executeAndFetchFirst(User.class);
        } catch (Sql2oException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM users";
            return con.createQuery(sql).executeAndFetch(User.class);
        }
    }

    @Override
    public void clearAll() {
        String sql1 = "TRUNCATE TABLE users";
        String sql2 = "ALTER SEQUENCE users_id_seq RESTART";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql1).executeUpdate();
            con.createQuery(sql2).executeUpdate();
        } catch (Sql2oException e) {
            System.out.println(e);
        }
    }

}
