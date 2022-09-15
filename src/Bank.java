import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    //create a new bank object with empty list of users and accounts
    public Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    //create an unique id and check it doesnt exist already
    public String getNewUserUUID(){
        //inits
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        do {
            //generate num
            uuid = "";
            for (int c=0; c<len; c++){
                uuid += ((Integer)rng.nextInt(10)).toString(); //picks a number between 0 and 9 six times
            }
            //check to make sure its unique
            nonUnique = false;
            for(User u: this.users){
                if (uuid.compareTo(u.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }


        } while(nonUnique);//keep looping while nonUnique is true ie uuid already exists

        return uuid;

    }

    public String getNewAccountUUID(){
        //inits
        String uuid;
        Random rng = new Random();
        int len =10;
        boolean nonUnique;

        do {
            //genrate num
            uuid = "";
            for (int c=0; c<len; c++){
                uuid += ((Integer)rng.nextInt(10)).toString(); //picks a number between 0 and 9 ten times
            }
            //check to make sure its unique
            nonUnique = false;
            for(Account a: this.accounts){
                if (uuid.compareTo(a.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }


        } while(nonUnique);//keep looping while nonUnique is true ie uuid already exists

        return uuid;


    }

    //this adds an account for the bank
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }

    //create new user of bank
    public User addUser(String firstName, String lastName, String pin){

        //add new user object to list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        //create a savings acc
        Account newAccount = new Account("Savings", newUser, this);
        //add to user and bank accs list
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;
    }


    //get user object ONLY if correct userID and pin used
    public User userLogin(String userID, String pin){
        //search thorugh list of users
        for(User u:this.users){

            //check user id is correct
            if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)){
                return u;
            }
        }
        //if we haven't found any matching details
        return null;
    }

    public String getName(){
        return this.name;
    }
}
