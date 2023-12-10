package flyxpert.flyxpert;

import java.util.Date;

public class Card extends PaymentMethod{
    private final String ownerName;
    private final String number;
    private final String expirationDate;
    private final String ccv;

    public Card(String ownerName, String number, String expirationDate, String ccv){
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
    public String getNumber() {
        return number;
    }
    public String getExpirationDate() {
        return expirationDate;
    }
    public String getCcv() {
        return ccv;
    }
}
