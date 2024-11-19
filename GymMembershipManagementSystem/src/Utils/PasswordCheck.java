/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author gAmma
 */
public class PasswordCheck {
    public static boolean checkLength(String password){
        if (password.length() <= 6){
            return true;
        }
        return false;
    }
}
