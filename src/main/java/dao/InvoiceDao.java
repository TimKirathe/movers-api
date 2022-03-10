package dao;

import models.Invoice;

import java.util.List;

public interface InvoiceDao {
    // create
    void save(Invoice invoice);

    //read
    List<Invoice> getAll();
    Invoice findById(int id);
    List<Invoice> findByUserId(int id);

    //delete
    void deleteById(int id);
    void clearAll();

    //update
}
