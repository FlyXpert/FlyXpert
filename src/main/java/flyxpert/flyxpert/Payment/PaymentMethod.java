package flyxpert.flyxpert.Payment;

public interface PaymentMethod {
    double calculateTotalPriceWithFees(double subTotal);
    String getPaymentMethodName();
}
