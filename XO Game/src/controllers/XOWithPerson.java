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
import view.Video;

/**
 *
 * @author moham
 */
public class XOWithPerson {

    protected JButton[][] board;
    protected char curPlayer;
    protected JPanel jpBoard;
    protected JLabel xScore;
    protected JLabel oScore;
    String move = "#";

    public XOWithPerson(JPanel p, JLabel xJLabel, JLabel oJLabel) {
        xScore = xJLabel;
        oScore = oJLabel;
        jpBoard = p;
        board = new JButton[3][3];
        curPlayer = 'x';
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
                        newTurn(Integer.valueOf(s[0]), Integer.valueOf(s[1]));
                    }
                });
                jpBoard.add(board[i][j]);
            }
        }
    }

    protected void newTurn(int x, int y) {
        fillCell(x, y);
        if (checkForWin()) {
            message(curPlayer);
            resetGame();
            return;
        }
        if (isBoardFull()) {
            message('d');
            resetGame();
        }
    }

    protected void resetGame() {
        jpBoard.removeAll();
        board = new JButton[3][3];
        curPlayer = 'x';
        initializeBoard();
        jpBoard.revalidate();
        jpBoard.repaint();
    }

    protected void message(char winner) {
        //Player 1 and 2 switched
//        JLabel picLabel = new JLabel(new ImageIcon(getClass().getResource("/icons/win.gif")));
        UIGameControl.setRecored(move, UIGameControl.getCurrentDate());
        switch (winner) {
            case 'x':
//                JOptionPane.showMessageDialog(jpBoard, picLabel, "O Win", JOptionPane.PLAIN_MESSAGE);
                Video.getInstance().start("O", true);
                setScore('o');
                break;
            case 'o':
//                JOptionPane.showMessageDialog(jpBoard, picLabel, "X Win", JOptionPane.PLAIN_MESSAGE);
                Video.getInstance().start("X", true);
                setScore('x');
                break;
            default:
                JOptionPane.showMessageDialog(jpBoard, "It's a draw", "Draw", JOptionPane.PLAIN_MESSAGE);
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

    protected boolean isBoardFull() {
        boolean isFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].getText().isEmpty()) {
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

    protected boolean checkHelper(JButton c1, JButton c2, JButton c3) {
        return ((!c1.getText().isEmpty()) && c1.getText().equalsIgnoreCase(c2.getText()) && c2.getText().equalsIgnoreCase(c3.getText()));
    }

    protected void changePlayer() {
        curPlayer = ((curPlayer == 'x') ? 'o' : 'x');
    }

    protected boolean fillCell(int row, int col) {
        if ((row >= 0) && (row < 3)) {
            if ((col >= 0) && (col < 3)) {
                if (board[row][col].getText().isEmpty()) {
                    board[row][col].setText("" + curPlayer);
                    board[row][col].setEnabled(false);
                    move += curPlayer + "%" + row + "%" + col + "@";
                    changePlayer();
                    return true;
                }
            }
        }
        return false;
    }
}
