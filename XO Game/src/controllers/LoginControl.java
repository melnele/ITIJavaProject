/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.swing.JOptionPane;
import model.Model;
import view.LoginForm;
import view.RegisterForm;
import view.UIGame;

/**
 *
 * @author AhmedG
 */
public class LoginControl {

    public String getUserName() {

        return Model.getUserName();
    }

    public String getPassword() {
        return Model.getPassWord();
    }

    public boolean chNamePassowrdIsEmpty(String userName, String password) {
        boolean ch = false;
        ch = !(userName.isEmpty() || password.isEmpty());
        return ch;
    }

    public boolean userLoginC(String userName, String passWord) {

        if (chNamePassowrdIsEmpty(userName, passWord)) {
            if (Model.userLogin(userName, passWord)) {
                JOptionPane.showMessageDialog(new LoginForm(), "Login Success.");
                uiGame();
                return true;
            } else {
                JOptionPane.showMessageDialog(new LoginForm(), "UserName / Password Incorrect.", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(new LoginForm(), "UserName / Password Shouldn't be Empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public void userRegisterC() {
        new RegisterForm().setTitle("Register a User");
        new RegisterForm().setLocationRelativeTo(null);
        new RegisterForm().setVisible(true);
    }

    public void uiGame() {
        new UIGame().setTitle("Game XO");
        new UIGame().setLocationRelativeTo(null);
        new UIGame().setVisible(true);
    }

}
