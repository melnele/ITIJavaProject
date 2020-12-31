/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import model.Model;

/**
 *
 * @author AhmedG
 */
public class UIGameControl {

    public int getWins() throws IOException {
        return Model.getWins();
    }

    public int getLoses() throws IOException {
        return Model.getLoses();
    }

    public int getDraws() throws IOException {
        return Model.getDraws();
    }

    public void setWins(int wins) throws IOException {
        Model.setWins(wins);
    }

    public void setLoses(int loses) throws IOException {
        Model.setLoses(loses);
    }

    public void setDraws(int draws) throws IOException {
        Model.setDraws(draws);
    }

    public String getUserName() {
        return Model.getUserName();
    }

    public String getDateTime() throws IOException {
        return Model.getDateTime();
    }

    public void setDateTime(String date) throws IOException {
        Model.setDateTime(date);
    }

    public String getRecord() throws IOException {
        return Model.getRecored();
    }

    public void setRecored(String record) throws IOException {
        Model.setRecored(record);
    }

}
