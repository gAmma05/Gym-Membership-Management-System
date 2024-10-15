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
public class User {
    private int id;
    private String username;
    private String password;
    private String salt;

    public User(){
        
    }
    
    public User(int id, String username, String password, String salt){
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    @Override
    public String toString(){
        return "User{" + id + ", " + username + ", " + password + ", " + salt + "}";
    }
    
}
