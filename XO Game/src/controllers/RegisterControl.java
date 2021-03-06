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
public class RegisterControl {

    public boolean chAllRegiIsEmpty(String userName, String password, String fName, String lName) {
        return !(userName.isEmpty() || password.isEmpty() || fName.isEmpty() || lName.isEmpty());
    }

    public boolean userRegisterC(String userName, String passWord, String fName, String lName, Component c) {
        if (chAllRegiIsEmpty(userName, passWord, fName, lName)) {
            if (Model.userRegister(fName, lName, userName, passWord)) {
                System.out.println( "User Add Success.");
                return true;
            } else {
                JOptionPane.showMessageDialog(c, "UserName Is Dublicated.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(c, "Fill Up The Form.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
