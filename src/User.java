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


}
