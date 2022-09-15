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
}
