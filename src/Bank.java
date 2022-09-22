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

    //create a unique id and check it doesn't exist already
    public String getNewUserCardNum(){
        //inits
        String cardStart;
        String cardNum;
        String zero;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        do {
            //generate num
            cardStart= "4929";
            cardNum = "";
//            zero = "00";
            for (int c=0; c<len; c++){
                cardNum = cardNum + ((Integer)rng.nextInt(10)).toString(); //picks a number between 0 and 9 twelve times
            }
            //check to make sure its unique
            nonUnique = false;
            for(User u: this.users){
                if (cardNum.compareTo(u.getCardNum()) == 0){
                    nonUnique = true;
                    break;
                }
            }


        } while(nonUnique);//keep looping while nonUnique is true ie card number already exists
//        String lastCardNum = cardNum.substring(cardNum.length()-1)+zero+cardNum.substring(cardNum.length()-1);
        StringBuilder sb = new StringBuilder(cardNum);
        zero = String.valueOf(sb.insert(cardNum.length()-1, "00"));
        cardNum = cardStart + zero;
        return cardNum;

    }

    public String getNewAccountAccNum(){
        //inits
        String accNum;
        Random rng = new Random();
        int len = 8;
        boolean nonUnique;

        do {
            //generate num
            accNum = "";
            for (int c=0; c<len; c++){
                accNum += ((Integer)rng.nextInt(10)).toString(); //picks a number between 0 and 9 eight times
            }
            //check to make sure its unique
            nonUnique = false;
            for(Account a: this.accounts){
                if (accNum.compareTo(a.getAccNum()) == 0){
                    nonUnique = true;
                    break;
                }
            }


        } while(nonUnique);//keep looping while nonUnique is true ie account number already exists

        return accNum;


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
    public User userLogin(String cardNum, String pin){
        //search through list of users
        for(User u:this.users){

            //check user id is correct
            if(u.getCardNum().compareTo(cardNum) == 0 && u.validatePin(pin)){
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


