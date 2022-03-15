package dao;

import models.Invoice;
import models.Service;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oInvoiceDao implements InvoiceDao {

    private final Sql2o sql2o;

    public Sql2oInvoiceDao(Sql2o sql2o)  {
        this.sql2o = sql2o;
    }

    @Override
    public void save(Invoice invoice) {
        try(Connection conn = sql2o.open()){
            String sql = "INSERT INTO invoice (userid, name, email, price, noofbedrooms, amount, latfrom, longfrom, latto, longto, date ) VALUES (:userId, :name, :email, :price, :noOfBedRooms, :amount, :latFrom, :longFrom, :latTo, :longTo, :date )";
            int id = (int) conn.createQuery(sql).bind(invoice)
                    .addParameter("userId", invoice.getUserId())
                    .addParameter("name", invoice.getName())
                    .addParameter("email", invoice.getEmail())
                    .addParameter("price", invoice.getPrice())
                    .addParameter("noOfBedRooms", invoice.getNoOfBedRooms())
                    .addParameter("amount", invoice.getAmount())
                    .addParameter("latFrom", invoice.getLatFrom())
                    .addParameter("longFrom", invoice.getLongFrom())
                    .addParameter("latTo", invoice.getLatTo())
                    .addParameter("longTo", invoice.getLongTo())
                    .addParameter("date", invoice.getDate())
                    .executeUpdate().getKey();
            invoice.setId(id);
        }
    }

    @Override
    public List<Invoice> getAll() {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM invoice";
            return con.createQuery(sql).executeAndFetch(Invoice.class);
        }
    }

    @Override
    public Invoice findById(int id) {
        try (Connection conn = sql2o.open()) {
            String sql = "SELECT * FROM invoice WHERE id = :id";
            return conn.createQuery(sql).addParameter("id", id)
                    .executeAndFetchFirst(Invoice.class);
        }
    }

    @Override
    public List<Invoice> findByUserId(int userId) {
        try (Connection conn = sql2o.open()) {
            String sql = "SELECT * FROM invoice WHERE userid = :userId";
            return conn.createQuery(sql).addParameter("userId", userId)
                    .executeAndFetch(Invoice.class);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        try(Connection conn = sql2o.open()) {
            String sql = "DELETE FROM invoice WHERE id = :id";
            conn.createQuery(sql).addParameter("id", id).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql1 = "TRUNCATE TABLE invoice";
        String sql2 = "ALTER SEQUENCE invoice_id_seq RESTART";
        try(Connection conn = sql2o.open()) {
            conn.createQuery(sql1).executeUpdate();
            conn.createQuery(sql2).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
