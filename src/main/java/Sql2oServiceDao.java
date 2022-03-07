import org.sql2o.*;

import java.util.List;

public class Sql2oServiceDao implements ServiceDao {

    private final Sql2o sql2o;

    public Sql2oServiceDao(Sql2o sql2o) {
        this.sql2o = sql2o;
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
