package flyxpert.flyxpert;

public class Payment {
    private static int allPaymentsMadeCount;
    private final int paymentID;
    private final double paymentAmount;
    private final PaymentMethod paymentMethod;

    Payment(double paymentAmount, PaymentMethod paymentMethod){
        this.paymentID = ++allPaymentsMadeCount;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
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
}
