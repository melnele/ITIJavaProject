/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author AhmedG
 */
public class ServerConnection {

    Socket mySocket;
    BufferedReader br;
    PrintStream ps;

    public ServerConnection() {
        try {
            mySocket = new Socket("127.0.0.1", 5005);
            br = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            ps = new PrintStream(mySocket.getOutputStream());
        } catch (IOException ex) {
        } catch (Exception e) {
            System.out.println("No Server");
        }
    }
}
