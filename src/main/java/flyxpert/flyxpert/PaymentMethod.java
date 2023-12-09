package flyxpert.flyxpert;

public abstract class PaymentMethod {
    protected int feesInDollars;

    protected double calculateTotalPriceWithFees(double subTotal){
        return subTotal + feesInDollars;
    }
}
