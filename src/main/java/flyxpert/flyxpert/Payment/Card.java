package flyxpert.flyxpert.Payment;
import flyxpert.flyxpert.Payment.PaymentMethod;
public class Card implements PaymentMethod {
    private String ownerName;
    private String number;
    private String expirationDate;
    private String ccv;
    public static final double FEES_PERCENTAGE = 0.03;

    public Card(){
        super();
    }
    public Card(String ownerName, String number, String expirationDate, String ccv){
        super();
        this.ownerName = ownerName;
        this.number = number;
        this.expirationDate = expirationDate;
        this.ccv = ccv;
    }

    public double calculateTotalPriceWithFees(double subTotal){
        return subTotal + (subTotal * FEES_PERCENTAGE);
    }

    public String getPaymentMethodName(){
        return "Card";
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    public void setCcv(String ccv) {
        this.ccv = ccv;
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
