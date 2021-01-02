/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.UIGame;

/**
 *
 * @author AhmedG
 */
public class Model {

    public static final ServerConnection connection = ServerConnection.getInstance();

    private static int id;
    private static String userName;

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
            if (!connection.br.ready()) {
                Thread.sleep(100);
            }
            String[] res = connection.br.readLine().trim().split("#");
            int wins = Integer.valueOf(res[0]);
            return wins;
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("No Server");
        } catch (InterruptedException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void setWins(int wins) {
        connection.ps.println("setwins#" + userName + "#" + wins);
    }

    public static int getLoses() {
        try {
            connection.ps.println("getloses#" + userName);
            if (!connection.br.ready()) {
                Thread.sleep(100);
            }
            String[] res = connection.br.readLine().trim().split("#");
            int loses = Integer.valueOf(res[0]);
            return loses;
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("No Server");
        } catch (InterruptedException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void setLoses(int loses) {
        connection.ps.println("setloses#" + userName + "#" + loses);
    }

    public static int getDraws() {
        try {
            connection.ps.println("getdraws#" + userName);
            if (!connection.br.ready()) {
                Thread.sleep(100);
            }
            String[] res = connection.br.readLine().trim().split("#");
            int Draws = Integer.valueOf(res[0]);
            return Draws;
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("No Server");
        } catch (InterruptedException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
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
            if (!connection.br.ready()) {
                Thread.sleep(100);
            }
            String str = connection.br.readLine().trim();
            String[] res = str.split("#");
            return res;

        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String[] getAllDate() {
        try {
            connection.ps.println("history#" + userName);
            if (!connection.br.ready()) {
                Thread.sleep(100);
            }
            String str = connection.br.readLine().trim();
            System.out.println("All Record : " + str);
            String[] res = str.split("#");
            for (String str1 : res) {
                System.out.println("one Record : " + str1);
            }
            return res;
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
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
            if (!connection.br.ready()) {
                Thread.sleep(100);
            }
            String[] res = connection.br.readLine().trim().split("#");
            String recored = res[0];
            System.out.println("Model UI getRecord" + recored);
            return recored;
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("No Server");
        } catch (InterruptedException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
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

    public static String checkMove() {
        String str = null;
        try {
            if (!connection.br.ready()) {
                Thread.sleep(100);
            }
            str = connection.br.readLine().trim();
            System.out.println("checkMove " + str);
        } catch (NullPointerException e) {
            System.out.println("No Server");
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }

    public static int[] getMove() {
        try {
            if (!connection.br.ready()) {
                Thread.sleep(100);
            }
            int x = Integer.valueOf(connection.br.readLine());
            System.out.println(x);
            int y = Integer.valueOf(connection.br.readLine());
            System.out.println(x + " " + y);
            return new int[]{x, y};
        } catch (NullPointerException e) {
            System.out.println("No Server");
        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void move(char player, int x, int y) {
        try {
            connection.ps.println(x);
            connection.ps.println(y);
//            String res = connection.br.readLine().trim();
//            System.out.println(res);
        } catch (NullPointerException e) {
            System.out.println("No Server");
        }
    }

    public static void playWith(String userName) {
        try {
            connection.ps.println("play#" + userName);
//            String res = connection.br.readLine().trim();
//            System.out.println(res);
        } catch (NullPointerException e) {
            System.out.println("No Server");
        }
    }

    public static boolean checkInvite() {
        try {
            int BUFFER_SIZE = 10000;

            if (!connection.br.ready()) {
                return false;
            }
            connection.br.mark(BUFFER_SIZE);
            String str = connection.br.readLine();
            if (Thread.interrupted()) {
                connection.br.reset();
                System.out.println("int " + str);
                return true;
            }
            System.out.println("checkInvite " + str);
            String[] res = str.trim().split("#");
            if (res[0].equalsIgnoreCase("ready") && res.length >= 2) {
                Object[] options = {"Accept", "Refuse"};
                int n = JOptionPane.showOptionDialog(UIGame.getUI(),
                        res[1] + " Sent you a game invite?",
                        "Game invite",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, //do not use a custom Icon
                        options, //the titles of buttons
                        options[0]); //default button title
                if (n == 0) {
                    connection.ps.println("yes#" + res[1]);
                    UIGame.getUI().acc();
                    return true;
                } /*else {
                    connection.ps.println("no#" + res[1]);
                    return false;
                }*/
            } else if (res[0].equalsIgnoreCase("yes")) {
                return true;
            } /*else if (res[0].equalsIgnoreCase("no")) {
                UIGame.getUI().ref();
                return true;
            }*/ else {
                connection.br.reset();
                return true;
            }
        } catch (IOException ex) {
            return true;
        }
        return false;
    }
}
