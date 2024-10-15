/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Utils.PasswordUtils;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author gAmma
 */
public class UserRegistration {
    public void Register(String username, String password) throws NoSuchAlgorithmException{
        try{
            String salt = PasswordUtils.generateSalt();
            
            String hashedPass = PasswordUtils.hashPassword(password, salt);
            
            String query = "INSERT INTO []"
        }
    }
}
