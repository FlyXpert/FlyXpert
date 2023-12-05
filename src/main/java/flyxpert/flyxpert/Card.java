package flyxpert.flyxpert;

import java.util.Date;

public class Card extends PaymentMethod{
    private String ownerName;
    private int number;
    private String expirationDate;
    private int ccv;

    public Card(String ownerName, int number, String expirationDate, int ccv){
        this.ownerName = ownerName;
        this.number = number;
        this.expirationDate = expirationDate;
        this.ccv = ccv;
    }

    public String getOwnerName(){
        return ownerName;
    }
    public void setOwnerName(String ownerName){
        this.ownerName = ownerName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCcv() {
        return ccv;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }
}
