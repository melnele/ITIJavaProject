/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author moham
 */
class ClientHandler extends Thread {

    private final Client client;
    public static Vector<Client> clientsList = new Vector<Client>();

    public ClientHandler(Socket cs) throws IOException {
        client = new Client(cs);
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!client.isInGame()) {
                    String str = client.getBr().readLine();
                    System.out.println(str);
                    String[] cmd = str.split("#");
                    if (cmd[0].equalsIgnoreCase("login")) {
                        loginHelper(cmd);
                    } else if (cmd[0].equalsIgnoreCase("register") && cmd.length >= 5) {
                        client.getPs().println(Model.userRegister(cmd[1], cmd[2], cmd[3], cmd[4]));
                    } else if (client.isLoggedin() && cmd[0].equalsIgnoreCase("clients")) {
                        for (int i = 0; i < clientsList.size(); i++) {
                            if (!clientsList.elementAt(i).getUserName().equalsIgnoreCase(client.getUserName())) {
                                client.getPs().print(clientsList.elementAt(i).getUserName() + "#");
                            }
                        }
                        client.getPs().println();
                    } else if (cmd[0].equalsIgnoreCase("play")) {
                        if (cmd.length >= 1) {
                            for (int i = 0; i < clientsList.size(); i++) {
                                if (clientsList.elementAt(i).getUserName().equalsIgnoreCase(cmd[1])) {
                                    client.setInGame(true);
                                    clientsList.elementAt(i).getPs().println("ready#" + client.getUserName());
                                }
                            }
                        }
                    } else if (cmd[0].equalsIgnoreCase("yes")) {
                        if (cmd.length >= 1) {
                            for (int i = 0; i < clientsList.size(); i++) {
                                if (clientsList.elementAt(i).getUserName().equalsIgnoreCase(cmd[1])) {
                                    client.setInGame(true);
                                    clientsList.elementAt(i).getPs().println("yes");
                                    NewGame newGame = new NewGame(client, clientsList.elementAt(i));
                                    newGame.start();
                                }
                            }
                        }
                    } /*else if (cmd[0].equalsIgnoreCase("no")) {
                        if (cmd.length >= 1) {
                            for (int i = 0; i < clientsList.size(); i++) {
                                if (clientsList.elementAt(i).getUserName().equalsIgnoreCase(cmd[1])) {
                                    clientsList.elementAt(i).setInGame(false);
                                    clientsList.elementAt(i).getPs().println("no");
                                }
                            }
                        }
                    } */ else if (client.isLoggedin() && cmd[0].equalsIgnoreCase("history")) {
                        client.getPs().println(Model.getAllDate(client));
                    } else if (cmd[0].equalsIgnoreCase("getwins")) {
                        client.getPs().println(Model.getWins(client));
                    } else if (cmd[0].equalsIgnoreCase("getdraws")) {
                        client.getPs().println(Model.getDraws(client));
                    } else if (cmd[0].equalsIgnoreCase("getloses")) {
                        client.getPs().println(Model.getLoses(client));
                    } else if (cmd[0].equalsIgnoreCase("setwins")) {
                        Model.setWins(client, Integer.valueOf(cmd[2]));
                    } else if (cmd[0].equalsIgnoreCase("setdraws")) {
                        Model.setDraws(client, Integer.valueOf(cmd[2]));
                    } else if (cmd[0].equalsIgnoreCase("setloses")) {
                        Model.setLoses(client, Integer.valueOf(cmd[2]));
                    } else if (cmd[0].equalsIgnoreCase("getrecord")) {
                        client.getPs().println(Model.getRecored(client, cmd[2]));
                    } else if (cmd[0].equalsIgnoreCase("setrecord")) {
                        Model.setRecored(client, cmd[2], cmd[3]);
                    } else if (cmd[0].equalsIgnoreCase("done")) {
                    }
                }
            } catch (IOException ex) {
                try {
                    client.getPs().close();
                    client.getBr().close();
                    client.getSocket().close();
                } catch (IOException ex1) {
                    System.out.println(ex1.getMessage());
                }
                clientsList.remove(client);
                return;
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
                    client.setLoggedin(true);
                } else {
                    client.getPs().println(false + "#Wrong UserName/Password");
                }
            }
        } else {
            client.getPs().println(false + "#Missing fields");
        }
    }
}
