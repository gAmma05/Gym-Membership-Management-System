/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author gAmma
 */
<<<<<<< HEAD
public class Trainer extends User {
=======
public class Trainer extends User{
>>>>>>> 3187e3ecc824429ce5e920f227a0c360f10903b6

    private String availableSessions; // Corresponds to 'availableSessions' column

    public Trainer() {
    }

<<<<<<< HEAD
    public Trainer(int id, String username, String password, String salt,
             String name, String email, String address, String city, String birth, String phoneNumber, String gender,
            String availableSessions) {
=======
    public Trainer(String availableSessions, int id, String username, String password, String salt, String name, String email, String address, String city, String birth, String phoneNumber, String gender) {
>>>>>>> 3187e3ecc824429ce5e920f227a0c360f10903b6
        super(id, username, password, salt, name, email, address, city, birth, phoneNumber, gender);
        this.availableSessions = availableSessions;
    }

    public String getAvailableSessions() {
        return availableSessions;
    }

    public void setAvailableSessions(String availableSessions) {
        this.availableSessions = availableSessions;
    }

    @Override
    public String toString() {
        return "Trainer{" + "availableSessions=" + availableSessions + '}';
    }
<<<<<<< HEAD
=======
    
    
>>>>>>> 3187e3ecc824429ce5e920f227a0c360f10903b6

}
