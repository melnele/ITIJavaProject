/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AhmedG
 */
public class Model {

    private static final Connection con = DBConnection.connectdb();
    private static PreparedStatement st = null;
    private static ResultSet res = null;
    private static Statement stat = null;

    private static int id;
    private static String userName;
    private static String passWord;
    private static String fName;
    private static String lName;
    private static int wins;
    private static int loses;
    private static int Draws;

    private Model() {
    }

    public static int getId() {
        return id;
    }

    public static String getUserName() {
        return userName;
    }

    public static int getWins() {
        return wins;
    }

    public static void setWins(int wins) {

        String query = "UPDATE  XOGAME.\"users\" SET wins=? WHERE id=?";
        try {
            st = con.prepareStatement(query);
            st.setInt(1, wins);
            st.setInt(2, getId());
            st.executeUpdate();
            Model.wins = wins;
        } catch (SQLException ex) {
            System.out.println("there problem with input Wins in DataBase");
        }

    }

    public static int getLoses() {
        return loses;
    }

    public static void setLoses(int loses) {
        String query = "UPDATE  XOGAME.\"users\" SET loses=? WHERE id=?";
        try {
            st = con.prepareStatement(query);
            st.setInt(1, loses);
            st.setInt(2, getId());
            st.executeUpdate();
            Model.loses = loses;

        } catch (SQLException ex) {
            System.out.println("there problem with input Loses in DataBase");
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int getDraws() {
        return Draws;
    }

    public static void setDraws(int Draws) {
        String query = "UPDATE  XOGAME.\"users\" SET draws=? WHERE id=?";
        try {
            st = con.prepareStatement(query);
            st.setInt(1, Draws);
            st.setInt(2, getId());
            st.executeUpdate();
            Model.Draws = Draws;
        } catch (SQLException ex) {
            System.out.println("there problem with input Draws in DataBase");
        }

    }

    public static boolean userLogin(String userName, String passWord) {
        boolean login = false;
        String query = "select * from XOGAME.\"users\" WHERE username = ? AND password = ?";
        try {
            st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, userName);
            st.setString(2, passWord);
            res = st.executeQuery();
            if (res.next()) {
                login = true;
                id = res.getInt("id");
               Model.userName=res.getString("username");
                System.out.println(getId() + " " + getUserName() + " " + getWins());
            }

        } catch (SQLException ex) {
            System.out.println("Model login failed EX User Login Model");
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return login;
    }

    public static boolean userRegister(String fName, String lName, String userName, String passWord) {
        boolean register = false;
        String query = "INSERT INTO XOGAME.\"users\" (fname,lname,username,password) VALUES(?,?,?,?)";
        try {
            st = con.prepareStatement(query);
            st.setString(1, fName);
            st.setString(2, lName);
            st.setString(3, userName);
            st.setString(4, passWord);
            if (st.executeUpdate() == 1) {
                register = true;
            }
        } catch (SQLException ex) {
            System.out.println("duplicate User Name");
            register = false;
        }
        return register;
    }

}
