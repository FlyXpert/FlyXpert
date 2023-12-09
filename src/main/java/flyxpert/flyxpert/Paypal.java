package flyxpert.flyxpert;

public class Paypal extends PaymentMethod{
    private final String email;

    public Paypal(String email){
        super();
        this.email = email;
        this.feesInDollars = 2;
    }

    public String getEmail() {
        return email;
    }
}
