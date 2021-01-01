/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private static String recored;

    private Model() {
    }

    public static int getId() {
        return id;
    }

    public static String getUserName() {
        return userName;
    }

    public static int getWins() {
        try {
            connection.ps.println("getwins#" + userName);
            String[] res = connection.br.readLine().trim().split("#");
            wins = Integer.valueOf(res[0]);
            return wins;
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("No Server");
        }
        return 0;
    }

    public static void setWins(int wins) {
        connection.ps.println("setwins#" + userName + "#" + wins);
    }

    public static int getLoses() {
        try {
            connection.ps.println("getloses#" + userName);
            String[] res = connection.br.readLine().trim().split("#");
            loses = Integer.valueOf(res[0]);
            return loses;
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("No Server");
        }
        return 0;
    }

    public static void setLoses(int loses) {
        connection.ps.println("setloses#" + userName + "#" + loses);
    }

    public static int getDraws() {
        try {
            connection.ps.println("getdraws#" + userName);
            String[] res = connection.br.readLine().trim().split("#");
            Draws = Integer.valueOf(res[0]);
            return Draws;
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("No Server");
        }
        return 0;
    }

    public static void setDraws(int draws) {
        connection.ps.println("setdraws#" + userName + "#" + draws);
    }

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

    public static String[] getAllDate() {
        try {
            connection.ps.println("history#" + userName);
            String str = connection.br.readLine().trim();
            System.out.println("All Record : " + str);
            String[] res = str.split("#");
            for (String str1 : res) {
                System.out.println("one Record : " + str1);
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

    public static String getRecored(String date) {
        try {
            connection.ps.println("getrecord#" + userName + "#" + date);
            String[] res = connection.br.readLine().trim().split("#");
            recored = res[0];
            System.out.println("Model UI getRecord" + recored);
            return recored;
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("No Server");
        }
        return "";
    }

    public static void setRecored(String recored, String date) {
        connection.ps.println("setrecord#" + userName + recored + "#" + date);
        System.out.println("ModelUI : " + recored);
    }

    public static String getCurrentDate() {
        LocalDateTime instance = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        String strDate = formatter.format(instance);
        return strDate;
    }
}
