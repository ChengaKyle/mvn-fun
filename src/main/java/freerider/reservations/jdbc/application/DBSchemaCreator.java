package freerider.reservations.jdbc.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Singleton component class that probes whether a schema (tables)
 * is present in a database and creates the schema if not.
 */
public class DBSchemaCreator {

    /**
     * Singleton {@link DBSchemaCreator} instance.
     */
    private static DBSchemaCreator dbSchema = null;

    /**
     * List of pairs: schema name and schema definition to create
     * in a database.
     */
    private final List<String> tableSQLinCreationOrder = List.of(
            Customer.tableName(), Customer.schema()
    );

    /**
     * Private constructor to avoid external instance creation as
     * part of the Singleton pattern.
     */
    private DBSchemaCreator() { }

    /**
     * Public getter method of {@link DBSchemaCreator} instance as
     * part of the Singleton pattern.
     * @return {@link DBSchemaCreator} singleton instance
     */
    public static DBSchemaCreator getInstance() {
        if(dbSchema==null) {
            dbSchema = new DBSchemaCreator();
        }
        return dbSchema;
    }

    /**
     * Probe that tables of the schema are present and create the ones
     * that are not.
     * @param dbcon open database connection
     * @return list of names of created tables
     */
    public List<String> probeCreateSchema(Connection dbcon) {
        List<String> tablesFound = new ArrayList<>();
        List<String> tablesCreated = new ArrayList<>();
        try(
                Statement stmt = dbcon.createStatement();
        ) {
            ResultSet rs = stmt.executeQuery("SHOW TABLES");
            while(rs.next()){
                var table = rs.getString(1);
                System.out.println(String.format(" --> found table: %s", table));
                tablesFound.add(table);
            }
            for(int i=0; i < tableSQLinCreationOrder.size()-1; i+=2) {
                var tableName = tableSQLinCreationOrder.get(i);
                if( ! tablesFound.contains(tableName)) {
                    var sql = tableSQLinCreationOrder.get(i+1);
                    try {
                        stmt.executeUpdate(sql);
                        tablesCreated.add(tableName);
                        //
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        tablesFound.clear();
        //
        return tablesCreated;
    }
}