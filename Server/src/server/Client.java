/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
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
    private final PrintStream ps;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        ps = new PrintStream(socket.getOutputStream());
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

    @Override
    public String toString() {
        return "Client{" + "userName=" + userName + ", id=" + id + ", fName=" + fName + ", lName=" + lName + '}';
    }
}
