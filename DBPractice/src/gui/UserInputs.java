package gui;

import java.util.Scanner;
import java.util.InputMismatchException;

public class UserInputs {

    static Scanner scan = new Scanner(System.in);

    public static int getInt() {
        int newInt = 0;
        try {
            newInt = scan.nextInt();
            scan.nextLine();
        } catch (InputMismatchException e) {
        }
        return newInt;
    }

    public static double getAmount() {
        double newDouble = 0;
        try {
            newDouble = scan.nextDouble();
        } catch (InputMismatchException e) {

        }

        return newDouble;
    }

    public static String getString() {
        String newString = "";
        newString = scan.nextLine();
        return newString;
    }

    public static double getWithdrawAmount() {
        System.out.print("Please Enter Withdraw Amount: $");
        double withdrawAmount = getAmount();
        return withdrawAmount;
    }

    public static double getDepositAmount() {
        System.out.print("Please Enter Deposit Amount: $");
        double depositAmount = getAmount();
        return depositAmount;
    }

    public static double getTransferAmount() {
        System.out.print("Please Enter Transfer Amount: $");
        double transferAmount = getAmount();
        return transferAmount;
    }

    public static String getFirstName() {
        String f_name = "";
        while (f_name.isBlank()) {
            System.out.print("Enter First Name: ");
            f_name = getString();
        }
        return f_name;
    }

    public static String getLastName() {
        String l_name = "";
        while (l_name.isBlank()) {
            System.out.print("Enter Last Name: ");
            l_name = getString();
        }
        return l_name;
    }

    public static String getUserName() {
        String userName = "";
        while (userName.isBlank()) {
            System.out.print("Enter Username: ");
            userName = getString();
        }
        return userName;
    }

}