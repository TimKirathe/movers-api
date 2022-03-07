import java.util.List;

public interface ServiceDao {

//    void save(Service service);

    Service findById(int id);

   List<Service> getAll();

    void clearAll();
}
