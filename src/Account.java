import java.util.ArrayList;

public class Account {
    //type of account
    private String name;
    //current bal
    private double balance;
    //accounts id
    private String uuid;
    //user object that owns account
    private User holder;
    //list of transactions
    private ArrayList<Transaction> transactions;

}
