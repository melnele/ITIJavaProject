/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author AhmedG
 */
public class DBConnection {

    static final String dbUrl = "jdbc:derby://localhost:1527/XOGame";
    static final String userNameDB = "XOGame";
    static final String passwordDB = "12345";

    public static Connection connectdb() {
        Connection con = null;
        try {
            // Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(dbUrl, userNameDB, passwordDB);
            System.out.println("Connection.............");
            return con;
        } catch (SQLException ex) {
            System.out.println("There Were Error While connection to DB.");

        }
        return null;
    }
}
