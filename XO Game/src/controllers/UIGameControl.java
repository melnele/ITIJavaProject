/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.swing.JPanel;
import model.Model;
import view.UIGame;

/**
 *
 * @author AhmedG
 */
public class UIGameControl {

    public int getWins() {
        return Model.getWins();
    }

    public int getLoses() {
        return Model.getLoses();
    }

    public int getDraws() {
        return Model.getDraws();
    }

    public String getUserName() {
        return  Model.getChUserName();
    }
    
    
}
