/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Component;
import javax.swing.JOptionPane;
import model.Model;

/**
 *
 * @author AhmedG
 */
public class LoginControl {

    public String getUserName() {
        return Model.getUserName();
    }

    public boolean chNamePassowrdIsEmpty(String userName, String password) {
        return !(userName.isEmpty() || password.isEmpty());
    }

    public boolean userLoginC(String userName, String passWord, Component c) {
        if (chNamePassowrdIsEmpty(userName, passWord)) {
            if (Model.userLogin(userName, passWord)) {
                JOptionPane.showMessageDialog(c, "Login Success.");
                return true;
            } else {
                JOptionPane.showMessageDialog(c, "UserName / Password Incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(c, "UserName / Password Shouldn't be Empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
