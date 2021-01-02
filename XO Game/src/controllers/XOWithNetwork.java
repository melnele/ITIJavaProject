/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Model;
import model.ServerConnection;
import view.UIGame;
import view.Video;

/**
 *
 * @author AhmedG
 */
public class XOWithNetwork extends Thread {

    protected JButton[][] board;
    protected char currentTurn;
    protected JPanel jpBoard;
    protected JLabel xScore;
    protected JLabel oScore;
    private final char player;
    String move = "#";

    public XOWithNetwork(char player, JPanel p, JLabel xJLabel, JLabel oJLabel) {
        xScore = xJLabel;
        oScore = oJLabel;
        jpBoard = p;
        board = new JButton[3][3];
        currentTurn = 'x';
        this.player = player;
        this.start();
        initializeBoard();
    }

    protected void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new JButton("");
                board[i][j].setFont(new Font("Arial", Font.PLAIN, 80));
                board[i][j].setForeground(new Color(249, 156, 147));
                board[i][j].setBackground(new Color(89, 125, 122));
                board[i][j].setName(i + " " + j);
                board[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String[] s = ((JButton) e.getSource()).getName().split(" ");
                        newTurn(new Integer(s[0]), new Integer(s[1]));
                    }
                });
                jpBoard.add(board[i][j]);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            switch (Model.checkMove()) {
                case "move":
                    int[] move = Model.getMove();
                    fillCell(move[0], move[1]);
                    break;
                case "win#x":
                    if (player == 'x') {
                        message('w');
                        setScore('x');
                    } else {
                        message('l');
                        setScore('o');
                    }
                    resetGame();
                    break;
                case "win#o":
                    if (player == 'o') {
                        message('w');
                        setScore('o');
                    } else {
                        message('l');
                        setScore('x');
                    }
                    resetGame();
                    break;
                case "draw":
                    message('d');
                    resetGame();
                    break;
                case "no":
                    UIGame.getUI().ref();
                    jpBoard.removeAll();
                    break;
                case "done":
                    UIGame.getUI().ref();
                    jpBoard.removeAll();
                    break;
            }
        }
    }

    protected void newTurn(int x, int y) {
        if (currentTurn == player) {
            Model.move(player, x, y);
            fillCell(x, y);
        } else {
            JOptionPane.showMessageDialog(jpBoard, "Please wait your turn", "Wait", JOptionPane.PLAIN_MESSAGE);
        }
    }

    protected void resetGame() {
        jpBoard.removeAll();
        board = new JButton[3][3];
        currentTurn = 'x';
        initializeBoard();
        jpBoard.revalidate();
        jpBoard.repaint();
    }

    protected void message(char winner) {
//        JLabel picLabel;
        switch (winner) {
            case 'w':
//                picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/win.gif")));
//                JOptionPane.showMessageDialog(jpBoard, picLabel, "WON", JOptionPane.PLAIN_MESSAGE);
                ServerConnection.getInstance().ps.println("cont");
                Video.getInstance().start(Model.getUserName(), true);
                break;
            case 'l':
//                picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/lose.gif")));
//                JOptionPane.showMessageDialog(jpBoard, picLabel, "LOST", JOptionPane.PLAIN_MESSAGE);
                ServerConnection.getInstance().ps.println("cont");
                Video.getInstance().start(Model.getUserName(), false);
                break;
            default:
                JOptionPane.showMessageDialog(jpBoard, "It's a draw", "Draw", JOptionPane.PLAIN_MESSAGE);
                ServerConnection.getInstance().ps.println("cont");
                break;
        }
    }

    protected void setScore(char c) {
        if (c == 'x') {
            String str_score = xScore.getText();
            int score = Integer.parseInt(str_score) + 1;
            xScore.setText(Integer.toString(score++));
        } else {
            String str_score = oScore.getText();
            int score = Integer.parseInt(str_score) + 1;
            oScore.setText(Integer.toString(score++));
        }
    }

    protected void changePlayer() {
        currentTurn = ((currentTurn == 'x') ? 'o' : 'x');
    }

    protected boolean fillCell(int row, int col) {
        if ((row >= 0) && (row < 3)) {
            if ((col >= 0) && (col < 3)) {
                if (board[row][col].getText().isEmpty()) {
                    board[row][col].setText("" + currentTurn);
                    board[row][col].setEnabled(false);
                    move += currentTurn + "%" + row + "%" + col + "@";
                    changePlayer();
                    return true;
                }
            }
        }
        return false;
    }
}
