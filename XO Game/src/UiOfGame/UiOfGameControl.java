/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiOfGame;

import loginandregister.Model;

/**
 *
 * @author AhmedG
 */
public class UiOfGameControl {

    public int getWin() {
        return Model.getWins();
    }

    public int getLose() {
        return Model.getLoses();
    }

    public int getDraw() {
        return Model.getDraws();
    }

//    public int getUserName() {
//        System.out.println("UI Model"+new Model().getChUserName());
//        return  new Model().getId();
//    }
}
