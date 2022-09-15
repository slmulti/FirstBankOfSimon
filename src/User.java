import java.util.ArrayList;

public class User {

    private String firstName;
    private String lastNAme;
    //ID number for the user
    private String uuid;
    //using MD5 hash to store the users pin number
    private byte pinHash[];
    //List of accounts for this user
    private ArrayList<Account> accounts;
}
