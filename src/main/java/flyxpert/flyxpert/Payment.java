package flyxpert.flyxpert;

public class Payment {
    public static int allPaymentsMadeCount;
    private final int paymentID;
    private final double paymentAmount;
    private final PaymentMethod paymentMethod;
    // Takes 2 values (Payed or Refunded)
    private String paymentStatus;

    Payment(double paymentAmount, PaymentMethod paymentMethod){
        this.paymentID = ++allPaymentsMadeCount;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        paymentStatus = "Payed";
    }

    public String toString() {
        return paymentMethod.getPaymentMethodName();
    }

    public int getPaymentID() {
        return paymentID;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus){
        this.paymentStatus = paymentStatus;
    }
}
