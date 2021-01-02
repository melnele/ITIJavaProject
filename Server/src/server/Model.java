package server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author AhmedG
 */
public class Model {

    private static final Connection con = DBConnection.connectdb();

    private Model() {
    }

    public static int getWins(Client client) {
        PreparedStatement st;
        ResultSet res;
        int wins = 0;
        String query = "select * from XOGAME.\"users\" WHERE id = ?";
        try {
            st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, client.getId());
            res = st.executeQuery();
            if (res.next()) {
                wins = res.getInt("wins");
            }
        } catch (SQLException ex) {
            System.out.println("Model getWins");
        }
        return wins;
    }

    public static void setWins(Client client, int wins) {
        PreparedStatement st;
        String query = "UPDATE  XOGAME.\"users\" SET wins=? WHERE id=?";
        try {
            st = con.prepareStatement(query);
            st.setInt(1, wins);
            st.setInt(2, client.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("there problem with input Wins in DataBase");
        }
    }

    public static int getLoses(Client client) {
        PreparedStatement st;
        ResultSet res;
        int loses = 0;
        String query = "select * from XOGAME.\"users\" WHERE id = ?";
        try {
            st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, client.getId());
            res = st.executeQuery();
            if (res.next()) {
                loses = res.getInt("loses");
            }
        } catch (SQLException ex) {
            System.out.println("Model getLoses");
        }
        return loses;
    }

    public static void setLoses(Client client, int loses) {
        PreparedStatement st;
        String query = "UPDATE  XOGAME.\"users\" SET loses=? WHERE id=?";
        try {
            st = con.prepareStatement(query);
            st.setInt(1, loses);
            st.setInt(2, client.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("there problem with input Loses in DataBase");
        }
    }

    public static int getDraws(Client client) {
        PreparedStatement st;
        ResultSet res;
        int draws = 0;
        String query = "select * from XOGAME.\"users\" WHERE id = ?";
        try {
            st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, client.getId());
            res = st.executeQuery();
            if (res.next()) {
                draws = res.getInt("draws");
            }
        } catch (SQLException ex) {
            System.out.println("Model getDraws");
        }
        return draws;
    }

    public static void setDraws(Client client, int Draws) {
        PreparedStatement st;
        String query = "UPDATE  XOGAME.\"users\" SET draws=? WHERE id=?";
        try {
            st = con.prepareStatement(query);
            st.setInt(1, Draws);
            st.setInt(2, client.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("there problem with input Draws in DataBase");
        }
    }

    public static boolean userLogin(String userName, String passWord, Client client) {
        PreparedStatement st;
        ResultSet res;
        boolean login = false;
        String query = "select * from XOGAME.\"users\" WHERE username = ? AND password = ?";
        try {
            st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, userName);
            st.setString(2, passWord);
            res = st.executeQuery();
            if (res.next()) {
                login = true;
                client.setId(res.getInt("ID"));
                client.setUserName(res.getString("USERNAME"));
            }
        } catch (SQLException ex) {
            System.out.println("Model login failed EX User Login Model");
        }
        return login;
    }

    public static boolean userRegister(String fName, String lName, String userName, String passWord) {
        PreparedStatement st;
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

    public static String getRecored(Client client, String dateTime) {
        PreparedStatement st;
        ResultSet res;
        String record = "";
        String query = "select * from XOGAME.\"recordandreplay\" WHERE username = ? AND dateandtime = ?";
        try {
            st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, client.getUserName());
            st.setString(2, dateTime);
            res = st.executeQuery();
            if (res.next()) {
                record = res.getString("record");
            }
        } catch (SQLException ex) {
            System.out.println("Model getRecord");
        }
        return record;
    }

    public static String getAllDate(Client client) {
        PreparedStatement st;
        ResultSet res;
        String dates = "";
        int i = 0;
        String query = "select * from XOGAME.\"recordandreplay\" WHERE username = ?";
        try {
            st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, client.getUserName());
            res = st.executeQuery();
            while (res.next()) {
                dates += res.getString("dateandtime");
                dates += "#";
            }
        } catch (SQLException ex) {
            System.out.println("Model getRecord");
        }
        return dates;
    }

    public static void setRecored(Client client, String record, String dateTime) {
        PreparedStatement st;
        String query = "INSERT INTO XOGAME.\"recordandreplay\" (username,record,dateandtime) VALUES(?,?,?)";
        try {
            st = con.prepareStatement(query);
            st.setString(1, client.getUserName());
            st.setString(2, record);
            st.setString(3, dateTime);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("there problem with input Record in DataBase");
        }
    }
}
