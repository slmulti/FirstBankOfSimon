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
}
