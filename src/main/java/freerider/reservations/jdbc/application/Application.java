package freerider.reservations.jdbc.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.StreamSupport;


/**
 * Main Application class.
 */
public class Application {

    /**
     * Program execution starts here.
     * @param args arguments passed from the command line
     */
    public static void main(String[] args) {
        System.out.println(String.format("Hello \"%s\" example!",
                Application.class.getName().replace(".application.Application", "")));

        /*
         * Provide database connection information.
         */
        String db_url = "jdbc:h2:mem:freerider";
        // String db_url = "jdbc:h2:file:./.database/freerider.h2";
        String db_user = "sa";
        String db_password = "";

        try(
                // try to open database connection
                Connection dbcon = DriverManager.getConnection(db_url, db_user, db_password)
        ) {
            var dbSchemaCreator = DBSchemaCreator.getInstance();
            var tablesCreated = dbSchemaCreator.probeCreateSchema(dbcon);
            var msg = tablesCreated.size()==0? "opened DB: all tables found" :
                    String.format(" --> opened DB: %d tables created", tablesCreated.size());
            System.out.println(msg);
            //
            var customerRepository = CustomerRepository.getInstance(dbcon);
            if(tablesCreated.size() > 0) {
                // insert records if table CUSTOMER was newly created
                if(tablesCreated.contains(Customer.tableName())) {
                    customerRepository.save("Eric", "Meyer");
                    customerRepository.save("Tony", "Allister");
                    customerRepository.save("Sandra", "Ohlstadt");
                    customerRepository.save("Erica", "Gronemann");
                    customerRepository.save("Khaleed", "Samadi");
                    customerRepository.save("Igor", "Medwedev");
                }
            }

            var customers = customerRepository.findAll();
            StreamSupport.stream(customers.spliterator(), false)
                    // print only Customer objects with even id
                    // .filter(customer -> customer.id() % 2 == 0)
                    //
                    // format and report customers
                    .map(customer -> String.format(" --> %s", customer))
                    .forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println(String.format("error opening database connection(%s, %s, %s): \"%s\"",
                    db_url, db_user, db_password, e.getMessage()));
        }
    }
}