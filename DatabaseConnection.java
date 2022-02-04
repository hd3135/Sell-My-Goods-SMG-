package sample;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * DatabaseConnection() class
 * java class to connect to MySQL Server databse running on localhost(my IP address)
 * this model represents objects carrying data
 * */



public class DatabaseConnection {
    public Connection databaseLink;
    /**
     *getConnection() class
     * setups connection between MySQL Server and the program
     * using the JDBC driver from MySQl called the mysql-connector .jar file
     * */


    public Connection getConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://192.168.88.62:3306/productdb";
            String username = "rootCapstoneUser";
            String password = "smg123";


            Class.forName(driver);
            databaseLink = DriverManager.getConnection(url, username, password);
            System.out.println("connected");

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }

}
