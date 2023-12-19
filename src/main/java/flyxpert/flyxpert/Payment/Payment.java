package flyxpert.flyxpert.Payment;

public class Payment {
    public static int allPaymentsMadeCount;
    private int paymentID;
    private final double paymentAmount;
    private final PaymentMethod paymentMethod;
    // Takes 2 values (Payed or Refunded)
    private String paymentStatus;

    public Payment(double paymentAmount, PaymentMethod paymentMethod){
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
    public void setPaymentID(int paymentID)
    {
        this.paymentID = paymentID;
    }
}
