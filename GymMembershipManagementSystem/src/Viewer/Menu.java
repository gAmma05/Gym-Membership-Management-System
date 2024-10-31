/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Access.UserLogin;
import Access.UserRegistration;
import Utils.Validation;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 *
 * @author gAmma
 */
public class Menu {

    public UserLogin ul = new UserLogin();
    public UserRegistration ur = new UserRegistration();
    

    public void Menu() throws NoSuchAlgorithmException, SQLException {
        int option = 0;
        do {
            System.out.println("WELCOME TO KIVOTOS GYM");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Your option: ");
            option = Validation.checkInt("");
            switch (option) {
                case 1:
                    Login();
                    break;

                case 2:
                    Register();
                    break;

                case 3:
                    System.out.println("Good bye");
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (option > 0 && option < 3);
    }

    private void Login() throws SQLException, NoSuchAlgorithmException {
        System.out.print("Username: ");
        String username = Validation.checkString("");

        System.out.print("Password: ");
        String password = Validation.checkString("");
        if (Validation.passwordLengthCheck(password) == false) {
            System.out.println("Password must be longer than 6 characters");
            return;
        }
        if (ul.Login(username, password) == true) {
            
        }
    }

    private void Register() throws NoSuchAlgorithmException, SQLException {
        System.out.print("Username: ");
        String username = Validation.checkString("");

        System.out.print("Password: ");
        String password = Validation.checkString("");
        if (Validation.passwordLengthCheck(password) == false) {
            System.out.println("Password must be longer than 6 characters");
            return;
        }

        System.out.print("Fullname: ");
        String name = Validation.checkString("");

        System.out.print("Email: ");
        String email = Validation.checkString("");

        String role = "";
        int roleN = 0;

        do {
            System.out.println("Choose role!");
            System.out.println("1. Member");
            System.out.println("2. Trainer");
            System.out.println("Your option (1 | 2): ");
            roleN = Validation.checkInt("");
            switch (roleN) {
                case 1:
                    role = "Member";
                    break;

                case 2:
                    role = "Trainer";
                    break;

                default:
                    System.out.println("Invalid input! Try again!");
                    break;

            }
        } while (roleN != 1 && roleN != 2);

        System.out.print("Phone number (length > 6): ");
        String phoneNumber = Validation.checkString("");
        if (phoneNumber.length() > 6) {
            ur.Register(username, password, name, email, role, phoneNumber);
        } else {
            System.out.println("Error");
        }

    }
}
