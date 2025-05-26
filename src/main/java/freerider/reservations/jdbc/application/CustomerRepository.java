package freerider.reservations.jdbc.application;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(long id);
    List<Customer> findAll();
    void deleteById(long id);
    void delete(Customer customer);
    long count();
}
