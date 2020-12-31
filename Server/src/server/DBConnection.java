package server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.derby.jdbc.ClientDriver;

/**
 *
 * @author AhmedG
 */
public class DBConnection {

    private static final String DB_URL = "jdbc:derby://localhost:1527/XOGame";
    private static final String DB_USERNAME = "XOGame";
    private static final String DB_PASSWORD = "12345";

    public static Connection connectdb() {
        try {
            DriverManager.registerDriver(new ClientDriver());
            Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connecting To Data Base.............");
            return con;
        } catch (SQLException ex) {
            System.out.println("There was an Error connecting to the DB.");
        }
        return null;
    }
}
