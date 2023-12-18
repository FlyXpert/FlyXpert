package flyxpert.flyxpert.Payment;

public class Paypal implements PaymentMethod {
    private String email;
    private final static double FEES_PERCENTAGE = 0.04;
    private final static double CONSTANT_FEE = 5;

    public Paypal(){
        super();
    }

    public Paypal(String email){
        super();
        this.email = email;
    }

    public double calculateTotalPriceWithFees(double subTotal){
        return subTotal + (subTotal * FEES_PERCENTAGE) + CONSTANT_FEE;
    }

    public String getPaymentMethodName(){
        return "Paypal";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
