/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Utils.Validation;
import java.util.Scanner;

/**
 *
 * @author gAmma
 */
public class TrainerMenu {
    public Scanner sc = new Scanner(System.in);
    
    private void setTrainSession(){
        System.out.println("Caution: 1 Trainer assigns only 1 Member");
        System.out.print("Select your member: ");
        int assignedID = Validation.checkInt("");
        
        
    }
}
