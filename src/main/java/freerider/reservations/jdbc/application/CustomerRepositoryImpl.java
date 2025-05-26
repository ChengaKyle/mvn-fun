package freerider.reservations.jdbc.application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepositoryImpl implements CrudRepository<Customer, Long> {

    private final Connection dbconnection;

    public CustomerRepositoryImpl(Connection dbconnection) {
        this.dbconnection = dbconnection;
    }

    @Override
    public Customer save(Customer customer) {
        if (customer.id() < 0) {
            // Insert
            try (PreparedStatement ps = dbconnection.prepareStatement(
                    "INSERT INTO CUSTOMER(FIRSTNAME, LASTNAME) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, customer.firstName());
                ps.setString(2, customer.lastName());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        long id = rs.getLong(1);
                        return new Customer(id, customer.firstName(), customer.lastName());
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Update
            try (PreparedStatement ps = dbconnection.prepareStatement(
                    "UPDATE CUSTOMER SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?")) {
                ps.setString(1, customer.firstName());
                ps.setString(2, customer.lastName());
                ps.setLong(3, customer.id());
                ps.executeUpdate();
                return customer;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("Fehler beim Speichern des Kunden");
    }

    @Override
    public Optional<Customer> findById(Long id) {
        try (PreparedStatement ps = dbconnection.prepareStatement("SELECT * FROM CUSTOMER WHERE ID = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Customer(
                            rs.getLong("ID"),
                            rs.getString("FIRSTNAME"),
                            rs.getString("LASTNAME")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Statement stmt = dbconnection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER")) {
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getLong("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public void deleteById(Long id) {
        try (PreparedStatement ps = dbconnection.prepareStatement("DELETE FROM CUSTOMER WHERE ID = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Customer customer) {
        deleteById(customer.id());
    }

    @Override
    public long count() {
        try (Statement stmt = dbconnection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM CUSTOMER")) {
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
