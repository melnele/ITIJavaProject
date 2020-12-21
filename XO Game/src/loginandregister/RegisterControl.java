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
public class RegisterControl {

    public String getUserName() {

        return Model.getUserName();
    }

    public String getPassword() {
        return Model.getPassWord();
    }

    public boolean chAllRegiIsEmpty(String userName, String password, String fName, String lName) {
        boolean ch = true;
        if (userName.isEmpty() || password.isEmpty() || fName.isEmpty() || lName.isEmpty()) {
            return ch;
        } else {
            ch = false;
        }
        return ch;
    }

    public boolean userRegiC(String userName, String passWord, String fName, String lName) {
        return Model.userRegister(fName, lName, userName, passWord);
    }
}
