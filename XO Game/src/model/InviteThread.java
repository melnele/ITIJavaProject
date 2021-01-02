/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author moham
 */
public class InviteThread extends Thread {

    @Override
    public void run() {
        while (true) {
            if (Model.checkInvite()) {
                return;
            }
        }
    }
}
