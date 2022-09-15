import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {

        //init scanner
        Scanner sc = new Scanner(System.in);

        //init Banks
        Bank theBank = new Bank("First Bank of Simon");

        //add a user, which also creatd a savings account
        User aUser = theBank.addUser("John", "Doe", "1234");

        //add current account
        Account newAccount = new Account("Current", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);


        //get the ATM to enter an infite loop
        User curUser;
        while(true){
            //stay in the log in screen until sucessful login
            curUser = ATM.mainMenuPrompt(theBank, sc);

            //stay in the main menu until user quits
            ATM.printUserMenu(curUser, sc);
        }
    }

    public static User mainMenuPrompt(Bank theBank, Scanner sc){

        //inits
        String userID;
        String pin;
        User authUser;

        //prompt user for user id/pin
        do{
            System.out.printf("\n\nWelcome to &s\n\n", theBank.getName());
            System.out.print("Enter user ID: ");
            userID = sc.nextLine();
            System.out.println("Enter pin: ");
            pin = sc.nextLine();

            //try to get the user object corresponding to the id and pin combo

            authUser = theBank.userLogin(userID, pin);
            if (authUser==null){
                System.out.println("Incorrect information entered." + "\nPlease try again.");
            }
        } while(authUser==null); //continue looping unitl sucessful login

        return authUser;
    }

    public static void printUserMenu(User theUser, Scanner sc) {

        //print a summary of the users account
        theUser.printAccountSummary();

        //init
        int choice;

        //user menu
        do{
            System.out.printf("Welcome %s, WHat would you like to do?", theUser.getFirstName());
            System.out.println(" 1) Show Account Transaction History");
            System.out.println(" 2) Make a Withdrawal");
            System.out.println(" 3) Make a Deposit");
            System.out.println(" 4) Make a Transfer");
            System.out.println(" 5) Quit");
            System.out.println();
            System.out.println("Enter Choice: ");
            choice = sc.nextInt();

            if (choice <1 || choice >5){
                System.out.println("Invalid choice. Please choose 1-5");
            }
        } while (choice >1 || choice <5);
            //process choice
            switch (choice){
                case 1:
                    ATM.showTransHistory(theUser, sc);
                    break;
                case 2:
                    ATM.withdrawFunds(theUser, sc);
                    break;
                case 3:
                    ATM.depositFunds(theUser, sc);
                    break;
                case 4:
                    ATM.transferFunds(theUser, sc);
                    break;
            }


            //redisplay menu unless quit
            //simple recursive call
            if (choice != 5){
                ATM.printUserMenu(theUser, sc);
            }
        }

        public static void showTransHistory(User theUser, Scanner sc){

            int theAcct;

            //get accout history
            do {
                System.out.printf("Enter the number (1-%d) of the account whose transactions you wnat to see: ", theUser.numAccounts());
                theAcct = sc.nextInt()-1; //zerobased indexing
                if(theAcct<0||theAcct>theUser.numAccounts()){
                    System.out.println("Invalid Selection. Please try again.");
                    }
            } while (theAcct>0||theAcct<theUser.numAccounts());
                //print trans history
            theUser.printAcctTransHistory(theAcct);

            }

        public static void transferFunds(User theUser, Scanner sc){

            //inits
            int fromAcct;
            int toAcct;
            double amount;
            double acctBal;

            //get the account to transfer from
            do {
                System.out.printf("Enter the number (1-%d) of the account to transfer from: ");
                fromAcct = sc.nextInt()-1;//zeroindex
                if(fromAcct  <0 || fromAcct > theUser.numAccounts()){
                    System.out.println("Invalid Selection. Please try again.");
                }
            } while (fromAcct  > 0 || fromAcct < theUser.numAccounts());
            acctBal = theUser.getAcctBalance();


        }


}

