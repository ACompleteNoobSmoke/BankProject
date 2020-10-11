package Program;

import gui1.UserInputs;
import gui1.menu;
import model.user;
import respiratory.Driver;

public class UserMethods {

    static menu menu = new menu();
    static Driver SQL = new Driver();

    // Method that contains the actions that the user can take.
    public user userActions(user bankUser) {
        int i = menu.userMenu(bankUser);
        String userName = bankUser.getUsername();
        switch (i) {
            case 1:
                withdrawalAction(userName);
                break;
            case 2:
                depositAction(userName);
                break;
            case 3:
                transferAction(userName);
                break;
            case 4:
                bankUser.profileView();
                break;
            case 5:
                bankUser = logoutAction(userName);
                return bankUser;
        }
        bankUser = SQL.loginFromDB(bankUser.getUsername(), bankUser.getPassword());
        return bankUser;
    }

    // Method to perform the withdrawal action.
    public void withdrawalAction(String userName) {
        System.out.println("    *** Withdrawal ***");
        int withdrawalAccount = menu.pickAccount();
        String accountString = UserInputs.getAccountString(withdrawalAccount);
        double amountFromDB = SQL.getAccountsFromDB(userName, accountString);
        if (amountFromDB < 0) {
            System.out.println(" Not Working");
            return;
        }
        double newAmount = method.performWithdraw(amountFromDB);
        SQL.updateMoney(userName, accountString, newAmount);
    }

    // Method to perform the deposit action.
    public void depositAction(String userName) {
        System.out.println("     *** Deposit ***");
        int depositAccount = menu.pickAccount();
        String accountString = UserInputs.getAccountString(depositAccount);
        double amountFromDB = SQL.getAccountsFromDB(userName, accountString);
        if (amountFromDB < 0) {
            System.out.println(" Not Working");
            return;
        }
        double newAmount = method.performDeposit(amountFromDB);
        SQL.updateMoney(userName, accountString, newAmount);
    }

    // Method to perform the transfer action.
    public void transferAction(String userName) {
        System.out.println("   *** Transfer ***");
        int choice = menu.pickAccountTransfer();
        String[] accountsTrans = UserInputs.getTransferAccountString(choice);
        String firstAccount = accountsTrans[0];
        String secondAccount = accountsTrans[1];
        double moneyFromDB1 = SQL.getAccountsFromDB(userName, firstAccount);
        double moneyFromDB2 = SQL.getAccountsFromDB(userName, secondAccount);
        double[] transfer = method.performTransfer(moneyFromDB1, moneyFromDB2);
        SQL.updateMoney(userName, firstAccount, transfer[0]);
        SQL.updateMoney(userName, secondAccount, transfer[1]);

    }

    // Method use to log out of the account.
    public user logoutAction(String userName) {
        System.out.println(userName + " Logging Out.....");
        return null;
    }

}
