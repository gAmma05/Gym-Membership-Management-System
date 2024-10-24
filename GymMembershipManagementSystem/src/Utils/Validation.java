/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Scanner;

/**
 *
 * @author gAmma
 */
public class Validation {
    private static Scanner sc = new Scanner(System.in);
    
    public static String checkString(String mess) {
        String value;
        while (true) {
            try {
                System.out.print(mess);
                value = sc.nextLine();
                if (value.isEmpty()) {
                    throw new Exception("Please input value!");
                }
                return value;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static int checkInt(String mess) {
        int value;
        while (true) {
            try {
                System.out.print(mess);
                value = Integer.parseInt(sc.nextLine());
                if (value <= 0) {
                    throw new Exception("Please input greater than 0!");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a int value!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static String checkYesNo(String mess) {
        String value;
        while (true) {
            try {
                System.out.print(mess);
                value = sc.nextLine();
                if (value.isEmpty()) {
                    throw new Exception("Please input value!");
                }
                if (value.equalsIgnoreCase("Y") || value.equalsIgnoreCase("N")) {
                    return value;
                } else {
                    throw new Exception("Please input Y = Yes Or N = No!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
