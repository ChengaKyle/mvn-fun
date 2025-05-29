package freerider.reservations.jdbc.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    private static CustomerRepository instance = null;
    private static Connection dbconnection = null;

    /**
     * Public getter method of {@link CustomerRepository} instance as
     * part of the Singleton pattern.
     * @param dbcon open database connection
     * @return {@link CustomerRepository} singleton instance
     */
    public static CustomerRepository getInstance(Connection dbcon) {
        if(instance == null){
            instance = new CustomerRepository();
            dbconnection = dbcon;
        }
        return instance;
    }


    /**
     * Method to save (insert) a new {@link Customer} object in the associated
     * database table with a new {@code id} assigned by the database.
     * @param firstName first name attribute of a customer
     * @param lastName last name attribute of a customer
     * @return {@link Customer} object with {@code id} assigned by
     *          the database or empty if object was not inserted
     */
    Optional<Customer> save(String firstName, String lastName) {
        //wenn null ILLEx
        if(firstName == null){
            throw new IllegalArgumentException("Attribut firstName fehlt");
        }
        if(lastName == null){
            throw new IllegalArgumentException("Attribut lastName fehlt");
        }
        try(
                PreparedStatement ps = dbconnection.prepareStatement("INSERT INTO CUSTOMER(FIRSTNAME, LASTNAME) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
        ){
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            int numRowsAffected = ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    long id = rs.getLong(1);
                    //ausgeben
                    System.out.println(String.format("inserted customer \"%s %s\"\twith id: %d (%d)", firstName,lastName, id, numRowsAffected));
                    return Optional.of(new Customer(id, firstName, lastName));
                }
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }


    /**
     * Method to retrieve an {@link Customer} object from the associated
     * database table by a provided {@code id}.
     * @param id {@code id} of the object to retrieve from the database
     * @return retrieved object if {@code id} was found or empty result
     */
    public Optional<Customer> findById(long id) {

        try(PreparedStatement ps = dbconnection.prepareStatement("SELECT * FROM CUSTOMER WHERE ID=?")){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    String firstName = rs.getString("FIRSTNAME");
                    //last
                    return Optional.of(new Customer(id, firstName, firstName));
                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }


    /**
     * Method to retrieve all {@link Customer} objects from the associated
     * database table.
     * @return all objects retreived from the associated database table
     */
    public Iterable<Customer> findAll() {
        //
        List<Customer> customersFound = new ArrayList<>();
        try(
                Statement stmt = dbconnection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER");
        ){
            while(rs.next()){
                long id = rs.getLong("ID");
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                Customer customer = new Customer(id, firstName, lastName);
                customersFound.add(customer);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return customersFound;
    }

}
