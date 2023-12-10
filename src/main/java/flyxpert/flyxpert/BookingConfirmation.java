package flyxpert.flyxpert;

import  java.util.Date;
import java.util.Random;

public class BookingConfirmation {
    private String name; //might delete later
    private static int lastBookingNumber = 1111234;
    private int bookingNumber;
    private String airLineName;
    private String cardExpirationDate;
    private Card cardPayment;
    private Paypal paypalPayment;
    private Price economyClassPrice;
    private String destination;
    private String departingDate;
    private Payment payment;
    public BookingConfirmation(String name, String price, String airLineName, PaymentMethod payingMethod, String destination, String departingDate){;
        this.name = name;
        this.economyClassPrice = new Price(price);
        this.airLineName = airLineName;
        setPaymentMethod(payingMethod);
        this.departingDate = departingDate;
        this.destination = destination;
        this.bookingNumber = lastBookingNumber++;
    }
//    public BookingConfirmation(BookingConfirmation bookingConfirmation){;
//        this.name = bookingConfirmation.name;
//        this.bookingNumber = bookingConfirmation.bookingNumber;
//        this.economyClassPrice = bookingConfirmation.economyClassPrice;
//        this.airLineName = bookingConfirmation.airLineName;
//        this.payingMethod = bookingConfirmation.payingMethod;
//    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAirLineName(String airLineName) {
        this.airLineName = airLineName;
    }
    public String getName() {
        return name;
    }
    public String getCardExpirationDate() {
        return cardExpirationDate;
    }
    public String getDepartingDate() {
        return departingDate;
    }
    public int getBookingNumber() {
        return bookingNumber;
    }
    public Price getPrice() {
        return this.economyClassPrice;
    }
    public String getAirLineName() {
        return this.airLineName;
    }
    public String getDestination(){
        return this.destination;
    }
    private void setPaymentMethod(PaymentMethod paymentMethod){
        if(paymentMethod instanceof Card){
            this.cardPayment = new Card(((Card) paymentMethod).getOwnerName(), ((Card) paymentMethod).getNumber(), ((Card) paymentMethod).getExpirationDate(), ((Card) paymentMethod).getCcv());
        }
        else if (paymentMethod instanceof Paypal){
            this.paypalPayment = new Paypal(((Paypal) paymentMethod).getEmail());
        }
    }
    public Card getCardPayment() {
        return cardPayment;
    }

    public Paypal getPaypalPayment() {
        return paypalPayment;
    }
}