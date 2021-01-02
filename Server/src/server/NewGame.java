/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;

/**
 *
 * @author moham
 */
public class NewGame extends Thread {

    private Client player1, player2;
    private char[][] board;
    private char curPlayer;

    public NewGame(Client player1, Client player2) {
        this.player1 = player1;
        this.player2 = player2;
        board = new char[3][3];
        initializeBoard();
        curPlayer = 'x';
    }

    @Override
    public void run() {
        while (true) {
            try {
                int x, y;
                if (curPlayer == 'x') {
                    x = Integer.valueOf(player1.getBr().readLine());
                    y = Integer.valueOf(player1.getBr().readLine());
                } else {
                    x = Integer.valueOf(player2.getBr().readLine());
                    y = Integer.valueOf(player2.getBr().readLine());
                }
                System.out.println(curPlayer + " " + x + " " + y);
                boolean filled = fillCell(x, y);

                if (filled) {
                    if (curPlayer == 'x') {
                        player1.getPs().println("move");
                        player1.getPs().println(x);
                        player1.getPs().println(y);
                    } else {
                        player2.getPs().println("move");
                        player2.getPs().println(x);
                        player2.getPs().println(y);
                    }
                    if (checkForWin()) {
                        changePlayer();
                        System.out.println("win#" + curPlayer);
                        player1.getPs().println("win#" + curPlayer);
                        player2.getPs().println("win#" + curPlayer);
                        if (player1.getBr().readLine().equalsIgnoreCase("done")) {
                            player2.getPs().println("done");
                            player1.setInGame(false);
                            player2.setInGame(false);
                            return;
                        }
                        if (player2.getBr().readLine().equalsIgnoreCase("done")) {
                            player1.getPs().println("done");
                            player1.setInGame(false);
                            player2.setInGame(false);
                            return;
                        }
                        resetGame();
                    }
                    if (isBoardFull()) {
                        System.out.println('d');
                        player1.getPs().println("draw");
                        player2.getPs().println("draw");
                        if (player1.getBr().readLine().equalsIgnoreCase("done")) {
                            player2.getPs().println("done");
                            player1.setInGame(false);
                            player2.setInGame(false);
                            return;
                        }
                        if (player2.getBr().readLine().equalsIgnoreCase("done")) {
                            player1.getPs().println("done");
                            player1.setInGame(false);
                            player2.setInGame(false);
                            return;
                        }
                        resetGame();
                    }
                }
            } catch (IOException ex) {
                try {
                    player1.getPs().close();
                    player1.getBr().close();
                    player1.getSocket().close();
                    player2.getPs().close();
                    player2.getBr().close();
                    player2.getSocket().close();
                } catch (IOException ex1) {
                    System.out.println(ex1.getMessage());
                }
                ClientHandler.clientsList.remove(player1);
                ClientHandler.clientsList.remove(player2);
                return;
            } catch (Exception e) {
                System.out.println(e.toString());
                return;
            }
        }
    }

    protected void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    protected void resetGame() {
        board = new char[3][3];
        curPlayer = 'x';
        initializeBoard();
    }

    protected boolean isBoardFull() {
        boolean isFull = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    protected boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    protected boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkHelper(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }
        return false;
    }

    protected boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkHelper(board[0][i], board[1][i], board[2][i]) == true) {
                return true;
            }
        }
        return false;
    }

    protected boolean checkDiagonalsForWin() {
        return ((checkHelper(board[0][0], board[1][1], board[2][2]) == true) || (checkHelper(board[0][2], board[1][1], board[2][0]) == true));
    }

    protected boolean checkHelper(char c1, char c2, char c3) {
        return ((c1 != ' ') && c1 == c2 && c2 == c3);
    }

    protected void changePlayer() {
        curPlayer = ((curPlayer == 'x') ? 'o' : 'x');
    }

    protected boolean fillCell(int row, int col) {
        if ((row >= 0) && (row < 3)) {
            if ((col >= 0) && (col < 3)) {
                if (board[row][col] == ' ') {
                    board[row][col] = curPlayer;
                    changePlayer();
                    return true;
                }
            }
        }
        return false;
    }
}
