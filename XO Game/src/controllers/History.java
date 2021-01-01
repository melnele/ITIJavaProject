/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author AhmedG
 */
public class History extends Thread {

    protected JButton[][] board;
    protected char curPlayer;
    protected JPanel jpBoard;
    protected JLabel xScore;
    protected JLabel oScore;
    String move = "#";
    String[] cmd;
    String moves;

    public History(JPanel p, JLabel xJLabel, JLabel oJLabel, String date) {
        xScore = xJLabel;
        oScore = oJLabel;
        jpBoard = p;
        board = new JButton[3][3];
        curPlayer = 'x';
        moves = UIGameControl.getRecord(date);
        cmd = moves.split("@");
        initializeBoard();
        this.start();
    }

    protected void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new JButton("");
                board[i][j].setFont(new Font("Arial", Font.PLAIN, 80));
                board[i][j].setForeground(new Color(249, 156, 147));
                board[i][j].setBackground(new Color(89, 125, 122));
                board[i][j].setName(i + " " + j);
                jpBoard.add(board[i][j]);
            }
        }
    }

    @Override
    public void run() {
        for (String cmd1 : cmd) {
            String[] onMove = cmd1.split("%");
            curPlayer = onMove[0].charAt(0);
            int row = Integer.valueOf(onMove[1]);
            int col = Integer.valueOf(onMove[2]);
            board[row][col].setText("" + curPlayer);
            board[row][col].setEnabled(false);
            System.out.println("PLayer : " + curPlayer + " row : " + row + " col : " + col);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
