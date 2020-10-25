package Program;

import java.util.ArrayList;

import gui1.UserInputs;
import gui1.menu;
import model.admin;
import model.user;
import respiratory.Driver;

public class AdminMethod {

    static menu menu = new menu();

    public admin adminChoices(admin admin) {
        int j = menu.adminMenu(admin);
        switch (j) {
            case 1:
                searchMethod();
                break;
            case 2:
                deleteMethod();
                break;
            case 3:
                viewMethod();
                break;
            case 4:
                admin = logoutAdmin();
                break;
        }

        return admin;
    }

    // Method to search Database for user. Either by Username or Full Name.
    public void searchMethod() {
        int searchPick = menu.adminSearchMenu();
        if (searchPick == 3)
            return;
        String searchName = UserInputs.getSearchString(searchPick);
        user foundName = null;
        if (searchPick == 2) {
            String[] fullName = searchName.split("\\s+");
            searchName = Driver.getUserNameByFullName(fullName[0], fullName[1]);
        }
        foundName = Driver.adminSearchUsername(searchName);
        System.out.println("");
        viewProfile(foundName);
    }

    // Method to delete user from Database. Either by UserName or Full Name.
    public void deleteMethod() {
        int deletePick = menu.adminDeleteMenu();
        boolean deletedUser = false;
        if (deletePick == 3) {
            deletedUser = deleteAll();
            deleteConfirm(deletePick, deletedUser);
            return;
        } else if (deletePick == 4) {
            return;
        }
        String deleteName = UserInputs.getSearchString(deletePick);

        if (deletePick == 2) {
            String fullName[] = deleteName.split("\\s+");
            deleteName = Driver.getUserNameByFullName(fullName[0], fullName[1]);
        }
        deletedUser = deleteUserDB(deleteName);
        deleteConfirm(deletePick, deletedUser);
    }

    public boolean deleteUserDB(String userName) {
        boolean deletedUser = false;
        deletedUser = Driver.deleteBankAccount(userName);
        if (deletedUser) {
            deletedUser = Driver.deleteBankSecurity(userName);
        }
        if (deletedUser) {
            deletedUser = Driver.adminDeleteUsername(userName);
        }
        return deletedUser;
    }

    public boolean deleteAll() {
        boolean deleteAll = false;
        deleteAll = Driver.deleteAccounts();
        deleteAll = Driver.deleteSecurity();
        deleteAll = Driver.deleteBankUser();
        return deleteAll;
    }

    public void deleteConfirm(int deletePick, boolean deleted) {
        if (deletePick == 1 || deletePick == 2) {
            if (deleted) {
                System.out.println("\nUser Has Been Deleted!\n");
            } else {
                System.out.println("\nUser Was Not Found");
                System.out.println("User Was Not Deleted!\n");
            }
        } else if (deletePick == 3) {
            if (deleted) {
                System.out.println("\nAll Profile Has Been Deleted\n");
            } else {
                System.out.println("\nAll Profiles Could Not Be Deleted!\n");
            }
        }
    }

    public void viewMethod() {
        int viewing = menu.adminViewMenu();
        if (viewing == 1) {
            searchMethod();
        } else if (viewing == 2) {
            ArrayList<user> userInDB = Driver.allUsers();
            viewAllProfile(userInDB);
        }
    }

    // Method to view user Profile.
    public void viewProfile(user viewUser) {
        if (viewUser != null) {
            UserMethods userMethod = new UserMethods();
            userMethod.viewAction(viewUser);
            return;
        }

        System.err.println("\nProfile Cannot Be Viewed!!\n");
    }

    // Method to view all the profile in the Database
    public void viewAllProfile(ArrayList<user> allBankUsers) {
        System.out.println("****All Profiles****\n");
        if (allBankUsers == null) {
            System.out.println("\nDatabase Is Currently Empty\n");
        } else {
            for (user printUser : allBankUsers) {
                System.out.println(printUser);
            }
        }
        System.out.println("*Press Anything To Continue!*");
        UserInputs.getString();
    }

    // Method to logout the admin.
    public admin logoutAdmin() {
        System.out.println("BASEDGOD OUT......");
        return null;
    }

}
