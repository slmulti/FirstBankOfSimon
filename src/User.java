import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {

    private String firstName;
    private String lastName;
    //ID number for the user
    private String uuid;
    //using MD5 hash to store the users pin
    private byte pinHash[];
    //List of accounts for this user
    private ArrayList<Account> accounts;

    //constructor to create new user
    public User(String firstName, String lastName, String pin, Bank theBank){
        //set users name
        this.firstName = firstName;
        this.lastName = lastName;

        //more secure to store the pins MD5 hash
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        //get a new unique universal id for user
        this.uuid = theBank.getNewUserUUID();

        //create empty list of accounts
        this.accounts = new ArrayList<Account>();

        //print log message
        System.out.printf("New user %s, %s, with ID %s created", lastName, firstName, this.uuid);
    }
    //encapsulation becasue using private array not public to restrict access
    //this adds an account for the user
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }

    //getter method
    public String getUUID(){
        return this.uuid;
    }

    //returns a boolean value of where or not the pin matches
    public boolean validatePin(String aPin){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()),
                    this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;

    }

    public String getFirstName(){
        return this.firstName;
    }

    //print summarys of the acocunt of this user
    public void printAccountSummary(){
        System.out.printf("\n\n%s's account summary, this.firstName");
        for(int a = 0; a <this.accounts.size(); a++){
            System.out.printf("%d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }


    //works out the number of accounts customer has
    public int numAccounts(){
        return this.accounts.size();
    }

    public void printAcctTransHistory(int acctIdx){
        this.accounts.get(acctIdx).printTransHistory();
    }

    //get a balance of particluar account
    public double getAcctBalance(int accIdx){
        return this.accounts.get(accIdx).getBalance();
    }


}
