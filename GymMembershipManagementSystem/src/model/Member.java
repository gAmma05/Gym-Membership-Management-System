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
public class Member extends User {

    private String joinDate;
    private int membershipId;

    public Member() {
    }

    public Member(int id, String username, String password, String salt,
             String name, String email, String role, String phoneNumber, String gender,
             String joinDate, int membershipId) {
        super(id, username, password, salt, name, email, role, phoneNumber, gender);
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

}
