/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moham
 */
public class Server {

    ServerSocket serverSocket;

    public Server() throws IOException {
        serverSocket = new ServerSocket(5005);

        while (true) {
            Socket s = serverSocket.accept();
            System.out.println("Starting Server.......");
            new ClientHandler(s).start();
        }
    }

    public static void main(String[] args) {
        try {
            Server lobbyServer = new Server();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
