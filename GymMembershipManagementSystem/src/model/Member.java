/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author gAmma
 */
public class Member extends User {

    private Date joinDate;
    private int membershipID;

    public Member() {
    }

    public Member(int id, String username, String password, String salt,
            String name, String email, String role, String phoneNumber, String gender,
            Date joinDate, int membershipID) {
        super(id, username, password, name, email, role, phoneNumber, gender);
        this.joinDate = joinDate;
        this.membershipID = membershipID;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
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
