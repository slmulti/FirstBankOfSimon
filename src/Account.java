import java.util.ArrayList;

public class Account {
    //type of account
    private String name;

    //accounts id
    private String uuid;
    //user object that owns account
    private User holder;
    //list of transactions
    private ArrayList<Transaction> transactions;

    //create new account constructor
    public Account(String name, User holder, Bank theBank){

        //set the account name and holder
        this.name = name;
        this.holder = holder;

        //get ew account UUID
        this.uuid = theBank.getNewAccountUUID();

        //init trans
        this.transactions = new ArrayList<Transaction>();




        }
    //getter
    public String getUUID(){
        return this.uuid;
    }

    //getting the summary line
    public String getSummaryLine(){

        //get the acc bal
        double balance = this.getBalance();

        //format the summary line, depending on whether the balance is negative
        if (balance >= 0){
            return String.format("%s : £%.02f : %s", this.uuid, balance, this.name);
        } else {
            return String.format("%s : £(%.02f) : %s", this.uuid, balance, this.name);
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
        System.out.printf("\nTransaction history for account %s\n", this.uuid);
        for(int t = this.transactions.size()-1; t>=0; t--){
            System.out.printf(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }
}
