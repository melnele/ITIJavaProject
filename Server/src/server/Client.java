/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author moham
 */
public class Client {

    private String userName;
    private int id;
    private String fName;
    private String lName;
    private final Socket socket;
    private boolean loggedin = false;
    private boolean inGame = false;
    private final PrintStream ps;
    private final BufferedReader br;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        ps = new PrintStream(socket.getOutputStream());
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getfName() {
        return fName;
    }

    public PrintStream getPs() {
        return ps;
    }

    public String getlName() {
        return lName;
    }

    public Socket getSocket() {
        return socket;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public BufferedReader getBr() {
        return br;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    @Override
    public String toString() {
        return "Client{" + "userName=" + userName + ", id=" + id + ", fName=" + fName + ", lName=" + lName + '}';
    }
}
