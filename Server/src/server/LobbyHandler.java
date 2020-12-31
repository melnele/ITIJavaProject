/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author moham
 */
class LobbyHandler extends Thread {

    private final Client client;
    private boolean loggedin = false;
    private final BufferedReader br;
    private static Vector<Client> clientsList = new Vector<Client>();

    public LobbyHandler(Socket cs) throws IOException {
        client = new Client(cs);
        br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
    }

    @Override
    public void run() {
        while (true) {

            try {
                String str = br.readLine();
                String[] cmd = str.split("#");
                if (cmd[0].equalsIgnoreCase("login")) {
                    loginHelper(cmd);
                } else if (cmd[0].equalsIgnoreCase("register") && cmd.length >= 5) {
                    client.getPs().println(Model.userRegister(cmd[1], cmd[2], cmd[3], cmd[4]));
                } else if (loggedin && cmd[0].equalsIgnoreCase("clients")) {
                    System.out.println("enter");
                    for (int i = 0; i < clientsList.size(); i++) {
                        if (!clientsList.elementAt(i).getUserName().equalsIgnoreCase(client.getUserName())) {
                            client.getPs().print(clientsList.elementAt(i).getUserName() + "#");
                        }
                    }
                    client.getPs().println();
                } else if (cmd[0].equalsIgnoreCase("getwins")) {
                    if (cmd.length >= 1) {
                        for (int i = 0; i < clientsList.size(); i++) {
                            if (clientsList.elementAt(i).getUserName().equalsIgnoreCase(cmd[1])) {
                                client.getPs().println(Model.getWins(clientsList.elementAt(i)));
                            }
                        }
                    }
                } else if (cmd[0].equalsIgnoreCase("getdraws")) {
                    if (cmd.length >= 1) {
                        for (int i = 0; i < clientsList.size(); i++) {
                            if (clientsList.elementAt(i).getUserName().equalsIgnoreCase(cmd[1])) {
                                client.getPs().println(Model.getDraws(clientsList.elementAt(i)));
                            }
                        }

                    }
                } else if (cmd[0].equalsIgnoreCase("getloses")) {
                    if (cmd.length >= 1) {
                        for (int i = 0; i < clientsList.size(); i++) {
                            if (clientsList.elementAt(i).getUserName().equalsIgnoreCase(cmd[1])) {
                                client.getPs().println(Model.getLoses(clientsList.elementAt(i)));
                            }
                        }

                    }
                } else if (cmd[0].equalsIgnoreCase("setwins")) {
                    if (cmd.length >= 2) {
                        for (int i = 0; i < clientsList.size(); i++) {
                            if (clientsList.elementAt(i).getUserName().equalsIgnoreCase(cmd[1])) {
                                Model.setWins(clientsList.elementAt(i), Integer.valueOf(cmd[2]));
                            }
                        }
                    }
                } else if (cmd[0].equalsIgnoreCase("setdraws")) {
                    if (cmd.length >= 2) {
                        for (int i = 0; i < clientsList.size(); i++) {
                            if (clientsList.elementAt(i).getUserName().equalsIgnoreCase(cmd[1])) {
                                Model.setDraws(clientsList.elementAt(i), Integer.valueOf(cmd[2]));
                            }
                        }
                    }
                } else if (cmd[0].equalsIgnoreCase("setloses")) {
                    if (cmd.length >= 2) {
                        for (int i = 0; i < clientsList.size(); i++) {
                            if (clientsList.elementAt(i).getUserName().equalsIgnoreCase(cmd[1])) {
                                Model.setLoses(clientsList.elementAt(i), Integer.valueOf(cmd[2]));
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                try {
                    client.getPs().close();
                    br.close();
                    client.getSocket().close();
                } catch (IOException ex1) {
                    System.out.println(ex1.getMessage());
                }
                clientsList.remove(client);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    private void loginHelper(String[] cmd) {
        if (cmd.length >= 3) {
            boolean flag = true;
            for (int i = 0; i < clientsList.size(); i++) {
                if (clientsList.elementAt(i).getUserName().equalsIgnoreCase(cmd[1])) {
                    client.getPs().println(false + "#Client already logged in");
                    flag = false;
                }
            }
            if (flag) {
                if (Model.userLogin(cmd[1], cmd[2], client)) {
                    clientsList.add(client);
                    client.getPs().println(true + "#" + client.getId());
                    loggedin = true;
                } else {
                    client.getPs().println(false + "#Wrong UserName/Password");
                }
            }
        } else {
            client.getPs().println(false + "#Missing fields");
        }
    }
}
