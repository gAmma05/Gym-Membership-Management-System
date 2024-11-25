/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author gAmma
 */
public class Trainer extends User {

    private LocalDate joinDate;
    private int expYear;

    public Trainer() {
    }

    public Trainer(int id, String username, String password, String name,
            String email, String role, String phoneNumber, String gender, LocalDate joinDate, int expYear) {
        super(id, username, password, name, email, role, phoneNumber, gender);
        this.joinDate = joinDate;
        this.expYear = expYear;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    @Override
    public String toString() {
        return "Trainer{" + "joinDate=" + joinDate + ", " + "expYear=" + expYear + '}';
    }

}
