package dao;

import models.Service;

import java.util.List;

public interface ServiceDao {

    // CREATE
    void save(Service service);

    // READ
    Service findById(int id);
    List<Service> getAll();

    // UPDATE

    // DELETE
    void deleteById(int id);
    void clearAll();
}
