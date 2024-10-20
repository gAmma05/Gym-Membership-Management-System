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
public class Member extends User{
    
    //Membership plan (Ví dụ:"Basic", "Premium"được giao cho mỗi member.
    private String membershipPlan;
    
    // Danh sách các entries tập luyện được ghi lại trong các buổi tập luyện của member.
    // Mỗi entry là một chuỗi mô tả chi tiết về bài tập, chẳng hạn như loại bài tập và thời lượng của bài tập.
    // Ví dụ: "2024-10-15: Cardio - 30 phút" hoặc "2024-10-16: Tập tạ - 45 phút" (trong tiếng Anh ghi: "2024-10-16: Weight Training - 45 minutes")
    private List<String> workoutHistory;
    
    // Map các thông số sức khỏe trong đó key là tên của thông số (ví dụ: "Weight", "BMI")
    // và giá trị là thông số tương ứng (ví dụ: 72,5 cho weight tính bằng kg).
    // Làm thế này cho phép truy cập nhanh vào các thông số cụ thể theo tên của chúng.
    //
    // Map được sử dụng vì mỗi thông số sức khỏe phải là duy nhất (ví dụ: ông chỉ có một giá trị cân nặng tại một thời điểm)
    // và ông muốn lưu trữ và truy xuất hiệu quả các cặp khóa-giá trị này.
    //
    // Ví dụ:
    // healthMetrics.put("Weight", 72,5); // Thêm hoặc cập nhật cân nặng của member thành 72,5 kg.
    // healthMetrics.put("BMI", 24,1); // Thêm hoặc cập nhật BMI của member thành 24,1.
    private Map<String, Double> healthMetrics;

    public Member() {
    }

    public Member(String membershipPlan, List<String> workoutHistory, Map<String, Double> healthMetrics, int id, String username, String password, String salt) {
        super(id, username, password, salt);
        this.membershipPlan = membershipPlan;
        this.workoutHistory = workoutHistory;
        this.healthMetrics = healthMetrics;
    }

    public String getMembershipPlan() {
        return membershipPlan;
    }

    public void setMembershipPlan(String membershipPlan) {
        this.membershipPlan = membershipPlan;
    }

    public List<String> getWorkoutHistory() {
        return workoutHistory;
    }

    public void setWorkoutHistory(List<String> workoutHistory) {
        this.workoutHistory = workoutHistory;
    }

    public Map<String, Double> getHealthMetrics() {
        return healthMetrics;
    }

    public void setHealthMetrics(Map<String, Double> healthMetrics) {
        this.healthMetrics = healthMetrics;
    }
    
    
    
    
}
