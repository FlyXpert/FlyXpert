package flyxpert.flyxpert;

public class Paypal implements PaymentMethod{
    private String email;
    private final double FEES_PERCENTAGE = 0.04;
    private final double CONSTANT_FEE = 5;

    public Paypal(){
        super();
    }

    public Paypal(String email){
        super();
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double calculateTotalPriceWithFees(double subTotal){
        return (subTotal * FEES_PERCENTAGE) + subTotal + CONSTANT_FEE;
    }

    public String getEmail() {
        return email;
    }
}
