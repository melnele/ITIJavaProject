/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginandregister;

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
        boolean ch = true;
        if (userName.isEmpty() || password.isEmpty()) {
            return ch;
        } else {
            ch = false;
        }
        return ch;
    }

    public boolean userLoginC(String userName, String passWord) {
        return Model.userLogin(userName, passWord);
    }

}
