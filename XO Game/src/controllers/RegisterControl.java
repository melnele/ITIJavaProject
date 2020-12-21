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

/**
 *
 * @author AhmedG
 */
public class RegisterControl {

    public boolean chAllRegiIsEmpty(String userName, String password, String fName, String lName) {
        boolean ch = false;
        ch = !(userName.isEmpty() || password.isEmpty() || fName.isEmpty() || lName.isEmpty());
        return ch;
    }

    public boolean userRegisterC(String userName, String passWord, String fName, String lName) {
        if (chAllRegiIsEmpty(userName, passWord, fName, lName)) {
            if (Model.userRegister(fName, lName, userName, passWord)) {
                JOptionPane.showMessageDialog(new RegisterForm(), "User Add.", "Success", JOptionPane.INFORMATION_MESSAGE);
                uiLogin();
                return true;
            } else {
                JOptionPane.showMessageDialog(new RegisterForm(), "UserName Is Dublicated.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(new RegisterForm(), "Fill Up The Form.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public void uiLogin() {
        new LoginForm().setTitle("Login a User");
        new LoginForm().setLocationRelativeTo(null);
        new LoginForm().setVisible(true);
    }
}
