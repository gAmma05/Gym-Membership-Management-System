/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author gAmma
 */
<<<<<<< HEAD
public class Member extends User {

    private String joinDate;
    private int membershipId;
=======
public class Member extends User{
    private String joinDate;    
    private int membershipId;     
>>>>>>> 3187e3ecc824429ce5e920f227a0c360f10903b6

    public Member() {
    }

<<<<<<< HEAD
    public Member(int id, String username, String password, String salt,
             String name, String email, String address, String city, String birth, String phoneNumber, String gender,
             String joinDate, int membershipId) {
=======
    public Member(String joinDate, int membershipId, int id, String username, String password, String salt, String name, String email, String address, String city, String birth, String phoneNumber, String gender) {
>>>>>>> 3187e3ecc824429ce5e920f227a0c360f10903b6
        super(id, username, password, salt, name, email, address, city, birth, phoneNumber, gender);
        this.joinDate = joinDate;
        this.membershipId = membershipId;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    @Override
    public String toString() {
        return "Member{" + "joinDate=" + joinDate + ", membershipId=" + membershipId + '}';
    }
<<<<<<< HEAD

=======
    
    
 
    
>>>>>>> 3187e3ecc824429ce5e920f227a0c360f10903b6
}
