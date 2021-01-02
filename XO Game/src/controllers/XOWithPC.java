/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.util.Duration;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Model;
import view.Video;

/**
 *
 * @author AhmedG
 */
public class XOWithPC extends XOWithPerson {

    public XOWithPC(JPanel p, JLabel xJLabel, JLabel oJLabel) {
        super(p, xJLabel, oJLabel);
    }

    @Override
    protected void newTurn(int x, int y) {
        fillCell(x, y);
        int[] move = getMove();
        if (checkForWin()) {
            message(curPlayer);
            resetGame();
        } else if (isBoardFull()) {
            message('d');
            resetGame();
        } else {
            if (move[0] != -1) {
                fillCell(move[0], move[1]);
            }
            if (checkForWin()) {
                message(curPlayer);
                resetGame();
            } else if (isBoardFull()) {
                message('d');
                resetGame();
            }
        }
    }

    @Override
    protected void message(char winner) {
        //Player 1 and 2 switched
        UIGameControl.setRecored(move, UIGameControl.getCurrentDate());
//        JLabel picLabel;
        switch (winner) {
            case 'x':
//                picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/lose.gif")));
//                JOptionPane.showMessageDialog(jpBoard, picLabel, "LOST", JOptionPane.PLAIN_MESSAGE);
                Model.setLoses(Model.getLoses() + 1);
                Video.getInstance().start("You", false);
                setScore('o');
                break;
            case 'o':
//                picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/win.gif")));
//                JOptionPane.showMessageDialog(jpBoard, picLabel, "WON", JOptionPane.PLAIN_MESSAGE);
                Model.setWins(Model.getWins() + 1);
                Video.getInstance().start("You", true);
                setScore('x');
                break;
            default:
                JOptionPane.showMessageDialog(jpBoard, "It's a draw", "Draw", JOptionPane.PLAIN_MESSAGE);
                Model.setDraws(Model.getDraws() + 1);
                break;
        }
    }

    protected int[] getMove() {
        char[][] boardCopy = new char[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].getText().length() <= 0) {
                    boardCopy[i][j] = ' ';
                } else {
                    boardCopy[i][j] = board[i][j].getText().charAt(0);
                }
            }
        }
        if (boardCopy[1][1] == ' ') {
            return new int[]{1, 1};
            // Return index of corner if middle is taken by player
        } else if (boardCopy[1][1] == 'x'
                && boardCopy[0][2] == ' ') {
            return new int[]{0, 2};
        }
        int[] computerIndex = findWinPosition(boardCopy, 'o');
        if (computerIndex[0] != -1) {
            return computerIndex;
        }
        // Return index of first winning position for player
        int[] playerIndex = findWinPosition(boardCopy, 'x');
        if (playerIndex[0] != -1) {
            return playerIndex;
        }
        // Return index of fork for computer
        computerIndex = findFork(boardCopy, 'o', 2);
        if (computerIndex[0] != -1) {
            return computerIndex;
        }

        playerIndex = findFork(boardCopy, 'x', 2);
        if (playerIndex[0] != -1) {
            // Play two in a row to counter fork
            if (boardCopy[1][1] == 'x') {
                return findFork(boardCopy, 'o', 1);
                // Play into fork position of player
            } else {
                return playerIndex;
            }
        }

        // Return index of first open spot
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardCopy[i][j] == ' ') {
                    return new int[]{i, j};
                }

            }
        }
        return new int[]{-1};
    }

    private static int[] findWinPosition(char[][] board, char piece) {
        char[][] boardCopy = copy(board);
        char[][] newBoardCopy;
        // Return index of first winning spot for piece
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newBoardCopy = copy(boardCopy);
                if (newBoardCopy[i][j] == ' ') {
                    newBoardCopy[i][j] = piece;
                    if (isWinner(newBoardCopy, piece) == true) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        // No win position found
        return new int[]{-1};
    }

    private static char[][] copy(char[][] board) {
        char[][] boardCopy = new char[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }
        return boardCopy;
    }

    private static boolean isWinner(char[][] board, char piece) {
        for (int i = 0; i < 3; i++) {
            if (checkHelper(board[i][0], board[i][1], board[i][2], piece) == true) {
                return true;
            }
            if (checkHelper(board[0][i], board[1][i], board[2][i], piece) == true) {
                return true;
            }
            if ((checkHelper(board[0][0], board[1][1], board[2][2], piece) == true)
                    || (checkHelper(board[0][2], board[1][1], board[2][0], piece) == true)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkHelper(char c1, char c2, char c3, char piece) {
        return (c1 == piece && c1 == c2 && c2 == c3);
    }

    private static int[] findFork(char[][] board, char piece, int wins) {
        char[][] boardCopy = copy(board);
        char[][] newBoardCopy;
        char[][] newBoardCopy2;
        int totalWins;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                totalWins = 0;
                newBoardCopy = copy(boardCopy);
                if (newBoardCopy[i][j] == ' ') {
                    newBoardCopy[i][j] = piece;
                }
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        newBoardCopy2 = copy(newBoardCopy);
                        if (newBoardCopy2[k][l] == ' ') {
                            newBoardCopy2[k][l] = piece;
                            if (isWinner(newBoardCopy2, piece)) {
                                totalWins++;
                            }
                            if (totalWins >= wins) {
                                return new int[]{i, j};
                            }
                        }
                    }
                }
            }
        }
        return new int[]{-1};
    }
}
