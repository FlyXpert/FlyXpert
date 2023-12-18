package flyxpert.flyxpert;

public interface PaymentMethod {
    double calculateTotalPriceWithFees(double subTotal);
    String getPaymentMethodName();
}
