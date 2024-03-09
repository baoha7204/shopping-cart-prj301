package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class utils {
    public static Connection createConnection() {
        Connection conn = null;
            String DB_URL = "jdbc:sqlserver://localhost\\SQLSERVER2:1433;"
                + "databaseName=ShoppingCart;";
            
            try {
                //connect to database
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection(DB_URL, "sa", "12345");
                //create statement
            } catch(ClassNotFoundException | SQLException ex) {
                System.out.println("Error connection");
            }
            return conn;
    }
}