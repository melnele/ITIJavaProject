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
    private static String chUserName;
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

    public static String getPassWord() {
        return passWord;
    }

    public static String getfName() {
        return fName;
    }

    public static String getlName() {
        return lName;
    }

    public static String getChUserName() {
        return chUserName;
    }

    public static void setChUserName(String chUserName) {
        Model.chUserName = chUserName;
    }

    public static int getWins() {
        return wins;
    }

    public static void setWins(int wins) {
        String query = "INSERT INTO XOGAME.\"users\" (wins) VALUES(?)";
        try {
            st = con.prepareStatement(query);
            st.setInt(1, wins);
            if (st.executeUpdate() == 1) {
                Model.wins = wins;
            }
        } catch (SQLException ex) {
            System.out.println("there problem with input Wins in DataBase");
        }

    }

    public static int getLoses() {
        return loses;
    }

    public static void setLoses(int loses) {
        String query = "INSERT INTO XOGAME.\"users\" (loses) VALUES(?) ";
        try {
            st = con.prepareStatement(query);
            st.setInt(1, loses);
            if (st.executeUpdate() == 1) {
                Model.loses = loses;
            }
        } catch (SQLException ex) {
            System.out.println("there problem with input Loses in DataBase");
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public static int getDraws() {
        return Draws;
    }

    public static void setDraws(int Draws) {
        String query = "INSERT INTO XOGAME.\"users\" (draws) VALUES(?)";
        try {
            st = con.prepareStatement(query);
            st.setInt(1, Draws);
            if (st.executeUpdate() == 1) {
                Model.Draws = Draws;
            }
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
                setChUserName(res.getString("username"));
                System.out.println(getId() + " " + getChUserName()+" "+getWins());
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
