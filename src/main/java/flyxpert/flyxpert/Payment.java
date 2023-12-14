package flyxpert.flyxpert;

public class Payment {
    private static int allPaymentsMadeCount;
    private final int paymentID;
    private final double paymentAmount;
    private final PaymentMethod paymentMethod;
    // Takes 2 values (Payed or Refunded)
    private final String paymentStatus;

    Payment(double paymentAmount, PaymentMethod paymentMethod){
        this.paymentID = ++allPaymentsMadeCount;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        paymentStatus = "Payed";
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
}
