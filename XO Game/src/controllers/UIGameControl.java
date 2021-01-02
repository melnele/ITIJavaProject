/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import model.Model;

/**
 *
 * @author AhmedG
 */
public class UIGameControl {

    public static int getWins() {
        return Model.getWins();
    }

    public static String[] getUsers() {
        return Model.getUsers();
    }

    public static int getLoses() {
        return Model.getLoses();
    }

    public static int getDraws() {
        return Model.getDraws();
    }

    public static void setWins(int wins) {
        Model.setWins(wins);
    }

    public static void setLoses(int loses) {
        Model.setLoses(loses);
    }

    public static void setDraws(int draws) {
        Model.setDraws(draws);
    }

    public static String getUserName() {
        return Model.getUserName();
    }

    public static String getRecord(String date) {
        return Model.getRecored(date);
    }

    public static String[] getAllDate() {
        return Model.getAllDate();
    }

    public static void setRecored(String record, String date) {
        Model.setRecored(record, date);
    }

    public static String getCurrentDate() {
        return Model.getCurrentDate();
    }

}
