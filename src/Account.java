import java.util.ArrayList;

public class Account {
    //type of account
    private String name;
    //accounts id
    private String accNum;
    //user object that owns account
    private User holder;
    //list of transactions
    private ArrayList<Transaction> transactions;

    //create new account constructor
    public Account(String name, User holder, Bank theBank){

        //set the account name and holder
        this.name = name;
        this.holder = holder;

        //get new account accNum
        this.accNum = theBank.getNewAccountAccNum();

        //init trans
        this.transactions = new ArrayList<Transaction>();

        }
    //getter
    public String getAccNum(){
        return this.accNum;
    }

    //getting the summary line
    public String getSummaryLine(){

        //get the acc bal
        double balance = this.getBalance();

        //format the summary line, depending on whether the balance is negative
        if (balance >= 0){
            return String.format("%s : £%.02f : %s", this.accNum, balance, this.name);
        } else {
            return String.format("%s : £(%.02f) : %s", this.accNum, balance, this.name);
        }
    }

    public double getBalance(){
        double balance = 0;
        for (Transaction t: this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }

    //Print transaction history of account
    public void printTransHistory(){
        System.out.printf("\nTransaction history for account %s\n", this.accNum);
        for(int t = this.transactions.size()-1; t>=0; t--){
            System.out.println(this.transactions.get(t).getSummaryLine());
        }

    }

    //create new transaction object and add to list
    public void addTransaction(double amount, String reference){

        Transaction newTrans = new Transaction(amount, reference, this);
        this.transactions.add(newTrans);
    }
}
