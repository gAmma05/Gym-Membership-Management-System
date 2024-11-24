/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author gAmma
 */
public class Member extends User {

    private LocalDate joinDate;
    private int membershipID;

    public Member() {
    }

    public Member(int id, String username, String password,
            String name, String email, String role, String phoneNumber, String gender,
            LocalDate joinDate, int membershipID) {
        super(id, username, password, name, email, role, phoneNumber, gender);
        this.joinDate = joinDate;
        this.membershipID = membershipID;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public int getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(int membershipID) {
        this.membershipID = membershipID;
    }

    @Override
    public String toString() {
        return "Member{" + "joinDate=" + joinDate + ", membershipId=" + membershipID + '}';
    }

}
