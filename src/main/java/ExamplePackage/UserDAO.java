package ExamplePackage;

import java.sql.*;

public class UserDAO {
    static Connection currentCon = null;
    static ResultSet rs = null;
    
    public static UserBean login(UserBean bean) {
        Statement stmt = null;
        
        String username = bean.getUsername();
        String password = bean.getPassword();
        
        String searchQuery = "SELECT * FROM users WHERE username='" 
                + username 
                + "' AND password='" 
                + password 
                + "'";
        
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Query: " + searchQuery);
        
        try {
            // Connect to database
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean userFound = rs.next();
            
            // If user does not exist
            if (!userFound) {
                System.out.println("User not found in database");
                bean.setValid(false);
            } 
            // If user exists
            else {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                
                System.out.println("Welcome " + firstName);
                bean.setFirstName(firstName);
                bean.setLastName(lastName);
                bean.setValid(true);
            }
        } catch (Exception ex) {
            System.out.println("Login failed: An Exception occurred: " + ex);
            ex.printStackTrace();
        } finally {
            // Close resources
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {}
                rs = null;
            }
            
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {}
                stmt = null;
            }
            
            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {}
                currentCon = null;
            }
        }
        
        return bean;
    }
}