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
public class Trainer extends User{
    private List<Member> assignedMembers;
    
    // Danh sách các buổi đào tạo có sẵn cho trainer.
    // Mỗi buổi được biểu diễn dưới dạng chuỗi, ghi rõ thời gian và địa điểm (ví dụ: "2024-10-20, 10:00 AM, Gym Room 1").
    //
    // Danh sách được sử dụng vì:
    // - Trainer có thể có nhiều buổi đã lên lịch.
    // - Các buổi cần được sắp xếp theo thời gian.
    //
    // Ví dụ:
    // availableSessions.add("2024-10-20, 10:00 AM, Gym Room 1"); // Thêm một buổi mới.
    // availableSessions.add("2024-10-21, 2:00 PM, Yoga Room"); // Thêm một buổi khác.
    private List<String> availableSessions;

    public Trainer() {
    }

    public Trainer(List<Member> assignedMembers, List<String> availableSessions, int id, String username, String password, String salt) {
        super(id, username, password, salt);
        this.assignedMembers = assignedMembers;
        this.availableSessions = availableSessions;
    }

    public List<Member> getAssignedMembers() {
        return assignedMembers;
    }

    public void setAssignedMembers(List<Member> assignedMembers) {
        this.assignedMembers = assignedMembers;
    }

    public List<String> getAvailableSessions() {
        return availableSessions;
    }

    public void setAvailableSessions(List<String> availableSessions) {
        this.availableSessions = availableSessions;
    }
    
    
    
}
