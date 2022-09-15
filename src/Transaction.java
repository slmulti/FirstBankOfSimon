import java.util.Date;

public class Transaction {
    //amount of trans
    private double amount;

    //time&date of trans
    private Date timestamp;

    //optional ref for trans
    private String reference;

    //the account used for the trans
    private Account inAccount;

    //two constructors but java will work out which one to use based on which arguments I pass through
    public Transaction(double amount, Account inAccount){
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.reference = "";
    }

    public Transaction(double amount, String reference, Account inAccount){
        //call the two arg constructor first
        this(amount, inAccount);
        //set the memo
        this.reference = reference;
    }


}
