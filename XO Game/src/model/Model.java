/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AhmedG
 */
public class Model {

    private static final ServerConnection connection = new ServerConnection();

    private static int id;
    private static String userName;
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

    /*public static void setWins(int wins) {
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
    }*/
    
    public static int getLoses() {
        return loses;
    }

    /*public static void setLoses(int loses) {
        String query = "UPDATE  XOGAME.\"users\" SET loses=? WHERE id=?";
        try {
            st = con.prepareStatement(query);
            st.setInt(1, loses);
            st.setInt(2, getId());
            st.executeUpdate();
            Model.loses = loses;

        } catch (SQLException ex) {
            System.out.println("there problem with input Loses in DataBase");
            //Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    public static int getDraws() {
        return Draws;
    }

    /*public static void setDraws(int Draws) {
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
    }*/
    
    public static String[] userLogin(String userName, String passWord) {
        try {
            connection.ps.println("login#" + userName + "#" + passWord);
            String[] res = connection.br.readLine().trim().split("#");
            System.out.println(res[1]);
            if (Boolean.valueOf(res[0])) {
                id = new Integer(res[1]);
                Model.userName = userName;
                System.out.println(getId() + " " + getUserName() + " " + getWins());
            }
            return res;
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("No Server");
            return new String[]{"" + false, "Server Error"};
        }
        return new String[]{"" + false, "Server Error"};
    }

    public static String[] getUsers() {
        try {
            connection.ps.println("clients#");
            String str = connection.br.readLine().trim();
            System.out.println(str);
           String[] res = str.split("#");
            for (String str1 : res) {
                System.out.println(str1);
            }
            return res;
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean userRegister(String fName, String lName, String userName, String passWord) {
        try {
            connection.ps.println("register#" + fName + "#" + lName + "#" + userName + "#" + passWord);
            return Boolean.parseBoolean(connection.br.readLine().trim());
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("No Server");
        }
        return false;
    }
}
