import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {

        //init scanner
        Scanner sc = new Scanner(System.in);

        //init Banks
        Bank theBank = new Bank("First Bank of Simon");

        //add a user, which also creates a savings account
        User User1 = theBank.addUser("Simon", "Love", "1234");
        User User2 = theBank.addUser("Haami", "Nyangibo", "1234");
        User User3 = theBank.addUser("Elon", "Musk", "1234");
//        User User4 = theBank.addUser("Karl", "Longworth", "1234");
//        User User5 = theBank.addUser("Jeff", "Bezos", "1234");



        //add current account
        Account newAccount1 = new Account("Current", User1, theBank);
        User1.addAccount(newAccount1);
        theBank.addAccount(newAccount1);

        Account newAccount2 = new Account("Current", User2, theBank);
        User2.addAccount(newAccount2);
        theBank.addAccount(newAccount2);

        Account newAccount3 = new Account("Current", User3, theBank);
        User3.addAccount(newAccount3);
        theBank.addAccount(newAccount3);

//        Account newAccount4 = new Account("Current", User4, theBank);
//        User4.addAccount(newAccount4);
//        theBank.addAccount(newAccount4);
//
//        Account newAccount5 = new Account("Current", User5, theBank);
//        User5.addAccount(newAccount5);
//        theBank.addAccount(newAccount5);

        //get the ATM to enter an infinite loop
        User curUser;
        while(true){
            //stay in the log in screen until successful login
            curUser = ATM.mainMenuPrompt(theBank, sc);

            //stay in the main menu until user quits
            ATM.printUserMenu(curUser, sc);
        }
    }

    public static User mainMenuPrompt(Bank theBank, Scanner sc){

        //init
        String cardNum;
        String pin;
        User authUser;

        //prompt user for user id/pin
        do{
            System.out.printf("\n\n=======================================\n----Welcome to %s!----\n=======================================\n\n", theBank.getName());
            System.out.print("Enter Card Number: ");
            cardNum = sc.nextLine();
            System.out.print("Enter Pin: ");
            pin = sc.nextLine();

            //try to get the user object corresponding to the id and pin combo

            authUser = theBank.userLogin(cardNum, pin);
            if (authUser==null){
                System.out.println("Incorrect information entered." + "\nPlease try again.");
            }
        } while(authUser==null); //continue looping until successful login

        return authUser;
    }

    public static void printUserMenu(User theUser, Scanner sc) {

        //print a summary of the users account
        theUser.printAccountSummary();

        //init
        int choice;

        //user menu
        do{
            System.out.println("");
            System.out.printf("Welcome %s, What would you like to\ndo today?\n", theUser.getFirstName());
            System.out.println(" 1) Show Account Transaction History");
            System.out.println(" 2) Make a Withdrawal");
            System.out.println(" 3) Make a Deposit");
            System.out.println(" 4) Make a Transfer");
            System.out.println(" 5) Quit");
            System.out.println();
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            if (choice < 1 || choice > 5){
                System.out.println("Invalid choice. Please choose 1-5");
            }
        } while (choice < 1 || choice > 5);
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
                case 5:
                    //gobble up the rest of previous input line
                    ATM.quit(sc);
                    sc.nextLine();
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

            //get account history
            do {
                System.out.println("");
                System.out.printf("Enter the number (1-%d) of the account\nwhose transactions you want to see: ", theUser.numAccounts());
                theAcct = sc.nextInt()-1; //zero-based indexing
                if(theAcct<0||theAcct>=theUser.numAccounts()){
                    System.out.println("Invalid Selection. Please try again.");
                    }
            } while (theAcct<0||theAcct>=theUser.numAccounts());
                //print trans history
            theUser.printAcctTransHistory(theAcct);

            }

            //complete transfer
    public static void transferFunds(User theUser, Scanner sc) {

        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;

        // get account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account to transfer from: ", theUser.numAccounts());
            fromAcct = sc.nextInt()-1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
        acctBal = theUser.getAcctBalance(fromAcct);

        // get account to transfer to
        do {
            System.out.printf("Enter the number (1-%d) of the account to transfer to: ", theUser.numAccounts());
            toAcct = sc.nextInt()-1;
            if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAcct < 0 || toAcct >= theUser.numAccounts());

        // get amount to transfer
        do {
            System.out.printf("Enter the amount to transfer (max £%.02f): £",
                    acctBal);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } else if (amount > acctBal) {
                System.out.printf("Amount must not be greater than balance of £%.02f.\n", acctBal);
            }
        } while (amount < 0 || amount > acctBal);

        // finally, do the transfer
        theUser.addAcctTransaction(fromAcct, -1*amount, String.format(
                "Transfer to account %s", theUser.getAcctNumber(toAcct)));
        theUser.addAcctTransaction(toAcct, amount, String.format(
                "Transfer from account %s", theUser.getAcctNumber(fromAcct)));

    }

        //process a withdrawal on the account
    public static void withdrawFunds(User theUser, Scanner sc) {
        //init
        int fromAcct;
        double amount;
        double acctBal;
        String reference;

        //get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account to withdraw from: ", theUser.numAccounts());
            fromAcct = sc.nextInt()-1;//zero-index
            if(fromAcct  < 0 || fromAcct >= theUser.numAccounts()){
                System.out.println("Invalid Selection. Please try again.");
            }
        } while (fromAcct  < 0 || fromAcct >= theUser.numAccounts());
        acctBal = theUser.getAcctBalance(fromAcct);

        //get the amount to transfer
        do {
            System.out.printf("Enter the amount to withdraw (max: £%.02f: £", acctBal);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero");
            } else if (amount > acctBal){
                System.out.printf("Amount must not be greater than\nbalance of £.02f", acctBal);
            }
        } while (amount < 0 || amount > acctBal );

        //gobble up the rest f previous input line
        sc.nextLine();

        //get reference
        System.out.print("Enter a Reference: ");
        reference = sc.nextLine();

        //do the withdrawal
        theUser.addAcctTransaction(fromAcct, -1*amount, reference);
    }

    public static void depositFunds(User theUser, Scanner sc){

        //init
        int toAcct;
        double amount;
        double acctBal;
        String reference;

        //get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account to deposit in: ", theUser.numAccounts());
            toAcct = sc.nextInt()-1;//zero-index
            if(toAcct  < 0 || toAcct > theUser.numAccounts()){
                System.out.println("Invalid Selection. Please try again.");
            }
        } while (toAcct  < 0 || toAcct >= theUser.numAccounts());
        acctBal = theUser.getAcctBalance(toAcct);

        //get the amount to transfer
        do {
            System.out.printf("Enter the amount to deposit: £", acctBal);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero");
            }
        } while (amount < 0);


        //gobble up the rest f previous input line
        sc.nextLine();

        //get reference
        System.out.print("Enter a Reference: ");
        reference = sc.nextLine();

        //do the withdrawal
        theUser.addAcctTransaction(toAcct, amount, reference);

    }

    public static void quit (Scanner sc){
        System.out.println();
        System.out.println("Thank you for banking with us today!");
        System.out.println("-------------Goodbye!---------------");
        System.out.println("************************************");
        System.out.println("************************************");
    }

}

