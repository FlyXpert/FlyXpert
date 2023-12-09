package flyxpert.flyxpert;

import java.util.Date;

public class Card extends PaymentMethod{
    private final String ownerName;
    private final int number;
    private final String expirationDate;
    private final int ccv;

    public Card(String ownerName, int number, String expirationDate, int ccv){
        super();
        this.ownerName = ownerName;
        this.number = number;
        this.expirationDate = expirationDate;
        this.ccv = ccv;
        this.feesInDollars = 1;
    }

    public String getOwnerName(){
        return ownerName;
    }
    public int getNumber() {
        return number;
    }
    public String getExpirationDate() {
        return expirationDate;
    }
    public int getCcv() {
        return ccv;
    }
}
