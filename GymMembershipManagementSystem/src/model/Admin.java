/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author gAmma
 */
public class Admin extends User {

    public Admin() {
    }

    public Admin(int id, String username, String password, String name, String email, String role, String phoneNumber, String gender) {
        super(id, username, password, name, email, role, phoneNumber, gender);
    }

    
}
