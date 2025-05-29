package ExamplePackage;

import java.sql.*;

public class ConnectionManager {
    private static final String DB_PATH = "jdbc:sqlite:db/users.db";
    private static Connection con = null;
    
    public static Connection getConnection() {
        try {
            // Load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            
            try {
                // Create database connection
                con = DriverManager.getConnection(DB_PATH);
                System.out.println("Connected to SQLite database successfully");
            } catch (SQLException ex) {
                System.out.println("Database connection failed: " + ex.getMessage());
                ex.printStackTrace();
            }
        } catch(ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found: " + e);
        }
        
        return con;
    }
    
    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}