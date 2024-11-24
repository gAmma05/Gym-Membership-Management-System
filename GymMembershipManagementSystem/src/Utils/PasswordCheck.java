/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author gAmma
 */
public class PasswordCheck {
    List<User> us = new ArrayList<>();
    
    public boolean checkLength(String password){
        if (password.length() <= 6){
            return true;
        }
        return false;
    }
    
    /*
    public boolean checkVerified(String password){
        for(User u : us){
            if(u.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
*/
}
