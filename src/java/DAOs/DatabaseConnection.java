/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author d00182295
 */
public class DatabaseConnection {
    
    private String databaseName;
    
    /**
     *constructor deals with the connection to database
     * @param databaseName the name of database
     */
    public DatabaseConnection (String databaseName)
    {
        this.databaseName = databaseName;
    }

    /**
     *The method connects with the jdbc driver to database
     * @return connection to find driver class
     */
    public Connection getConnection(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/librarydb";
        String username = "root";
        String password = "";
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex1) {
            System.out.println("Failed to find driver class " + ex1.getMessage());
            System.exit(1);
        } catch (SQLException ex2) {
            System.out.println("Connection failed " + ex2.getMessage());
        }
        return con;
    }
    
    /**
     * closing the connection if parameter is blank
     * @param con the connection with the database by jdbc driver
     */
    public void freeConnection(Connection con){
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to free connection: " + e.getMessage());
            System.exit(1);
        }
    }
}
