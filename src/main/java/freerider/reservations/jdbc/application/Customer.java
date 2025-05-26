package freerider.reservations.jdbc.application;

/**
 * Entity type of a {@link Customer} object associated with records stored in
 * a <i>CUSTOMER</i> table in a database.
 */
public record Customer(

        /**
         * Primary key as customer identity, assigned by the database
         * when a record is created in the database.
         */
        long id,

        /**
         * First name attribute of a customer.
         */
        String firstName,

        /**
         * Last name attribute of a customer.
         */
        String lastName

) {

    /**
     * Class-level method to return the name of associated database table.
     * @return name of the database table for customer records.
     */
    public static String tableName() { return "CUSTOMER"; }

    /**
     * Class-level method to return the SQL to create the table.
     */
    public static String schema() { return
            "CREATE TABLE CUSTOMER (" +
                    "  ID INTEGER NOT NULL AUTO_INCREMENT, " +
                    "  FIRSTNAME VARCHAR(255), " +
                    "  LASTNAME VARCHAR(255), " +
                    "  PRIMARY KEY ( ID )" +
                    ")";
    }

    /**
     * Constructor of an object unbound in the database (illegal negative id).
     * @param firstName first name attribute of a customer
     * @param lastName last name attribute of a customer
     */
    public Customer(String firstName, String lastName) {
        this(-1L, firstName, lastName);
    }
}