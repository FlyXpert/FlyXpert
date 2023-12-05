package flyxpert.flyxpert;

public class Paypal extends PaymentMethod{
    private String email;

    public Paypal(String email){
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
