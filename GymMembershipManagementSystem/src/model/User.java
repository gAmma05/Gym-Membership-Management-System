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
    private String name;        
    private String email;       
    private String address;     
    private String city;        
    private String birth;       
    private String phoneNumber;  
    private String gender;   

    public User() {
    }

    public User(int id, String username, String password, String salt, String name, String email, String address, String city, String birth, String phoneNumber, String gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.name = name;
        this.email = email;
        this.address = address;
<<<<<<< HEAD
=======
        this.city = city;
>>>>>>> 3187e3ecc824429ce5e920f227a0c360f10903b6
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

<<<<<<< HEAD
=======
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

>>>>>>> 3187e3ecc824429ce5e920f227a0c360f10903b6
    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + ", name=" + name + ", email=" + email + ", address=" + address + ", city=" + city + ", birth=" + birth + ", phoneNumber=" + phoneNumber + ", gender=" + gender + '}';
    }
    
    
    
}
