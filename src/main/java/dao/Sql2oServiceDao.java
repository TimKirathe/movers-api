package dao;

import models.Service;
import org.sql2o.*;

import java.util.List;

public class Sql2oServiceDao implements ServiceDao {

    private final Sql2o sql2o;

    public Sql2oServiceDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(Service service) {
        try(Connection con = sql2o.open()) {
            String sql = "INSERT INTO services (noofbedrooms, photolink, price) VALUES (:noOfRooms, :photoLink, :price)";
            int id = (int) con.createQuery(sql).bind(service)
                    .addParameter("noOfRooms", service.getNoOfBedRooms())
                    .addParameter("photoLink", service.getPhotoLink())
                    .addParameter("price", service.getPrice())
                    .executeUpdate().getKey();
            service.setId(id);
        }
    }

    @Override
    public Service findById(int id) {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM services WHERE id = :id";
            return con.createQuery(sql).addParameter("id", id)
                    .executeAndFetchFirst(Service.class);
        }
    }

    @Override
    public List<Service> getAll() {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM services";
            return con.createQuery(sql).executeAndFetch(Service.class);
        }
    }

    @Override
    public void deleteById(int id) {
        try(Connection con = sql2o.open()) {
            String sql = "DELETE FROM services WHERE id = :id";
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        } catch (Sql2oException e) {
            System.out.println(e);
        }
    }

    @Override
    public void clearAll() {
        String sql1 = "TRUNCATE TABLE services";
        String sql2 = "ALTER SEQUENCE services_id_seq RESTART";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql1).executeUpdate();
            con.createQuery(sql2).executeUpdate();
        } catch (Sql2oException e) {
            System.out.println(e);
        }
    }
}
