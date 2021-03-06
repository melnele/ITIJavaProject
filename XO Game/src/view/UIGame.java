/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controllers.History;
import controllers.UIGameControl;
import controllers.XOWithNetwork;
import controllers.XOWithPC;
import controllers.XOWithPerson;
import model.InviteThread;
import model.Model;
import model.ServerConnection;

/**
 *
 * @author maria
 */
public class UIGame extends javax.swing.JFrame {

    /**
     * Creates new form ProjectXO
     */
    XOWithPerson xoWithPerson;
    XOWithPC xoWithPC;
    History history;
    XOWithNetwork xoWithNetwork;
    InviteThread inviteThread;
    private static UIGame uiGame = null;

    private UIGame() {
        initComponents();
        jluserList.setListData(UIGameControl.getUsers());
        jLHistory.setListData(UIGameControl.getAllDate());
        jtUserNameUi.setText(UIGameControl.getUserName());
        jLWinUi.setText(String.valueOf(UIGameControl.getWins()));
        jLDrawUi.setText(String.valueOf(UIGameControl.getDraws()));
        jLLoseUi.setText(String.valueOf(UIGameControl.getLoses()));
        inviteThread = new InviteThread();
        inviteThread.start();
    }

    public static UIGame getUI() {
        if (uiGame == null) {
            uiGame = new UIGame();
        }
        return uiGame;
    }

    public void acc() {
        inviteThread.interrupt();
        bBack.setEnabled(false);
        jpParent.removeAll();
        jpParent.add(jpGame);
        xoWithNetwork = new XOWithNetwork('x', jpBoard, xScore, oScore);
        jpParent.repaint();
        jpParent.revalidate();
    }

    public void ref() {
        jpParent.removeAll();
        jpParent.add(jpMainScreen);
        jpParent.repaint();
        jpParent.revalidate();
        inviteThread = new InviteThread();
        inviteThread.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpParent = new javax.swing.JPanel();
        jpMainScreen = new javax.swing.JPanel();
        bPlayWithFriend = new javax.swing.JButton();
        bPlayWithPc = new javax.swing.JButton();
        bExit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbUserName = new javax.swing.JLabel();
        lbScore = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLWinUi = new javax.swing.JLabel();
        jLLoseUi = new javax.swing.JLabel();
        jLDrawUi = new javax.swing.JLabel();
        jtUserNameUi = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jluserList = new javax.swing.JList<>();
        bPlayWithSelectedPlayer = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLHistory = new javax.swing.JList<>();
        jpGame = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bBack = new javax.swing.JButton();
        jpBoard = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        xScore = new javax.swing.JLabel();
        oScore = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 500));

        jpParent.setBackground(new java.awt.Color(153, 153, 153));
        jpParent.setToolTipText("");
        jpParent.setName(""); // NOI18N
        jpParent.setLayout(new java.awt.CardLayout());

        jpMainScreen.setBackground(new java.awt.Color(249, 156, 147));

        bPlayWithFriend.setBackground(new java.awt.Color(195, 202, 198));
        bPlayWithFriend.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        bPlayWithFriend.setForeground(new java.awt.Color(89, 125, 122));
        bPlayWithFriend.setText("Play With Friends");
        bPlayWithFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPlayWithFriendActionPerformed(evt);
            }
        });

        bPlayWithPc.setBackground(new java.awt.Color(195, 202, 198));
        bPlayWithPc.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        bPlayWithPc.setForeground(new java.awt.Color(89, 125, 122));
        bPlayWithPc.setText("Play With PC");
        bPlayWithPc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPlayWithPcActionPerformed(evt);
            }
        });

        bExit.setBackground(new java.awt.Color(195, 202, 198));
        bExit.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        bExit.setForeground(new java.awt.Color(89, 125, 122));
        bExit.setText("Exit Game");
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(195, 202, 198));

        lbUserName.setBackground(new java.awt.Color(195, 202, 198));
        lbUserName.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbUserName.setForeground(new java.awt.Color(89, 125, 122));
        lbUserName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUserName.setText("User Name :");
        lbUserName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbScore.setBackground(new java.awt.Color(195, 202, 198));
        lbScore.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbScore.setForeground(new java.awt.Color(89, 125, 122));
        lbScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbScore.setText("Win :");
        lbScore.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setBackground(new java.awt.Color(195, 202, 198));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(89, 125, 122));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Lose :");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setBackground(new java.awt.Color(195, 202, 198));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(89, 125, 122));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Draw :");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLWinUi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLWinUi.setForeground(new java.awt.Color(0, 0, 51));

        jLLoseUi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLLoseUi.setForeground(new java.awt.Color(0, 0, 51));

        jLDrawUi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLDrawUi.setForeground(new java.awt.Color(0, 0, 51));

        jtUserNameUi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jtUserNameUi.setForeground(new java.awt.Color(0, 0, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtUserNameUi, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(lbScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLWinUi, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLLoseUi, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(jLDrawUi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jtUserNameUi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addComponent(jLLoseUi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbScore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLWinUi, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLDrawUi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jluserList);

        bPlayWithSelectedPlayer.setBackground(new java.awt.Color(195, 202, 198));
        bPlayWithSelectedPlayer.setFont(new java.awt.Font("Sylfaen", 1, 10)); // NOI18N
        bPlayWithSelectedPlayer.setForeground(new java.awt.Color(89, 125, 122));
        bPlayWithSelectedPlayer.setText("Play With Seleted Player");
        bPlayWithSelectedPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPlayWithSelectedPlayerActionPerformed(evt);
            }
        });

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        btnRefresh.setToolTipText("");
        btnRefresh.setBorderPainted(false);
        btnRefresh.setContentAreaFilled(false);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnHistory.setBackground(new java.awt.Color(195, 202, 198));
        btnHistory.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        btnHistory.setForeground(new java.awt.Color(89, 125, 122));
        btnHistory.setText("History");
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jLHistory);

        javax.swing.GroupLayout jpMainScreenLayout = new javax.swing.GroupLayout(jpMainScreen);
        jpMainScreen.setLayout(jpMainScreenLayout);
        jpMainScreenLayout.setHorizontalGroup(
            jpMainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpMainScreenLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(jpMainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bPlayWithFriend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bPlayWithPc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpMainScreenLayout.createSequentialGroup()
                        .addGroup(jpMainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jpMainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bPlayWithSelectedPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bExit, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)))
                .addGap(92, 92, 92))
        );
        jpMainScreenLayout.setVerticalGroup(
            jpMainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMainScreenLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(bPlayWithPc, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(bPlayWithFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpMainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMainScreenLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(jpMainScreenLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(bPlayWithSelectedPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefresh)
                        .addGap(37, 37, 37)))
                .addGroup(jpMainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMainScreenLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMainScreenLayout.createSequentialGroup()
                        .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)))
                .addComponent(bExit, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jpParent.add(jpMainScreen, "card2");

        jpGame.setBackground(new java.awt.Color(195, 202, 198));
        jpGame.setPreferredSize(new java.awt.Dimension(400, 550));

        jLabel1.setBackground(new java.awt.Color(251, 162, 118));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(89, 125, 122));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Game Started");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        bBack.setBackground(new java.awt.Color(195, 202, 198));
        bBack.setForeground(new java.awt.Color(195, 202, 198));
        bBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        bBack.setBorder(null);
        bBack.setBorderPainted(false);
        bBack.setContentAreaFilled(false);
        bBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBackActionPerformed(evt);
            }
        });

        jpBoard.setBackground(new java.awt.Color(249, 156, 147));
        jpBoard.setPreferredSize(new java.awt.Dimension(400, 400));
        jpBoard.setLayout(new java.awt.GridLayout(3, 3));

        jLabel2.setBackground(new java.awt.Color(251, 162, 118));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(89, 125, 122));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Player X : ");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setBackground(new java.awt.Color(251, 162, 118));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(89, 125, 122));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Player O :");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        xScore.setBackground(new java.awt.Color(251, 162, 118));
        xScore.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        xScore.setForeground(new java.awt.Color(89, 125, 122));
        xScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xScore.setText("0");
        xScore.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        oScore.setBackground(new java.awt.Color(251, 162, 118));
        oScore.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        oScore.setForeground(new java.awt.Color(89, 125, 122));
        oScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        oScore.setText("0");
        oScore.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jpGameLayout = new javax.swing.GroupLayout(jpGame);
        jpGame.setLayout(jpGameLayout);
        jpGameLayout.setHorizontalGroup(
            jpGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpGameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGameLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(oScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32))
                    .addGroup(jpGameLayout.createSequentialGroup()
                        .addComponent(bBack, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(78, 78, 78))))
        );
        jpGameLayout.setVerticalGroup(
            jpGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGameLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jpGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bBack, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(jpGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(xScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(oScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addComponent(jpBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpParent.add(jpGame, "card5");

        getContentPane().add(jpParent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBackActionPerformed
//        ServerConnection.getInstance().ps.println("done");
        inviteThread.interrupt();
        jpParent.removeAll();
        jpBoard.removeAll();
        xScore.setText("" + 0);
        oScore.setText("" + 0);
        jpParent.add(jpMainScreen);
        jluserList.setListData(UIGameControl.getUsers());
        jLHistory.setListData(UIGameControl.getAllDate());
        jtUserNameUi.setText(UIGameControl.getUserName());
        jLWinUi.setText(String.valueOf(UIGameControl.getWins()));
        jLDrawUi.setText(String.valueOf(UIGameControl.getDraws()));
        jLLoseUi.setText(String.valueOf(UIGameControl.getLoses()));
        jpParent.repaint();
        jpParent.revalidate();
        inviteThread = new InviteThread();
        inviteThread.start();
    }//GEN-LAST:event_bBackActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        inviteThread.interrupt();
        jluserList.setListData(Model.getUsers());
        jLHistory.setListData(UIGameControl.getAllDate());
        inviteThread = new InviteThread();
        inviteThread.start();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void bPlayWithSelectedPlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlayWithSelectedPlayerActionPerformed
        if (jluserList.getSelectedValue() != null) {
            bBack.setEnabled(false);
            Model.playWith(jluserList.getSelectedValue());
            jpParent.removeAll();
            jpParent.add(jpGame);
            xoWithNetwork = new XOWithNetwork('o', jpBoard, xScore, oScore);
            jpParent.repaint();
            jpParent.revalidate();
        }
    }//GEN-LAST:event_bPlayWithSelectedPlayerActionPerformed

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bExitActionPerformed

    private void bPlayWithPcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlayWithPcActionPerformed
        inviteThread.interrupt();
        jpParent.removeAll();
        jpParent.add(jpGame);
        xoWithPC = new XOWithPC(jpBoard, xScore, oScore);
        jpParent.repaint();
        jpParent.revalidate();
    }//GEN-LAST:event_bPlayWithPcActionPerformed

    private void bPlayWithFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlayWithFriendActionPerformed
        inviteThread.interrupt();
        jpParent.removeAll();
        jpParent.add(jpGame);
        xoWithPerson = new XOWithPerson(jpBoard, xScore, oScore);
        jpParent.repaint();
        jpParent.revalidate();
    }//GEN-LAST:event_bPlayWithFriendActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        if (jLHistory.getSelectedValue() != null) {
            inviteThread.interrupt();
            jpParent.removeAll();
            jpParent.add(jpGame);
            history = new History(jpBoard, xScore, oScore, jLHistory.getSelectedValue());
            jpParent.repaint();
            jpParent.revalidate();
        }
    }//GEN-LAST:event_btnHistoryActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBack;
    private javax.swing.JButton bExit;
    private javax.swing.JButton bPlayWithFriend;
    private javax.swing.JButton bPlayWithPc;
    private javax.swing.JButton bPlayWithSelectedPlayer;
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel jLDrawUi;
    private javax.swing.JList<String> jLHistory;
    private javax.swing.JLabel jLLoseUi;
    private javax.swing.JLabel jLWinUi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> jluserList;
    private javax.swing.JPanel jpBoard;
    private javax.swing.JPanel jpGame;
    private javax.swing.JPanel jpMainScreen;
    private javax.swing.JPanel jpParent;
    private javax.swing.JLabel jtUserNameUi;
    private javax.swing.JLabel lbScore;
    private javax.swing.JLabel lbUserName;
    private javax.swing.JLabel oScore;
    private javax.swing.JLabel xScore;
    // End of variables declaration//GEN-END:variables
}
