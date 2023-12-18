package flyxpert.flyxpert.Payment;
public class Price {
    private String economyClassPrice;
    private String bussniessClassPrice;
    private String firstClassPrice;
    public Price(String economyClassPrice) {
        this.economyClassPrice = economyClassPrice;
        this.bussniessClassPrice = Float.toString(Float.parseFloat(this.economyClassPrice) * 2);
        this.firstClassPrice = Float.toString(Float.parseFloat(this.economyClassPrice) * 5);
    }
    public Price(float economyClassPrice) {
        this.economyClassPrice = Float.toString(economyClassPrice);
        this.bussniessClassPrice = Float.toString(Float.parseFloat(this.economyClassPrice) * 2);
        this.firstClassPrice = Float.toString(Float.parseFloat(this.economyClassPrice) * 5);
    }
    public void setEconomyClassPrice(String economyClassPrice) {
        this.economyClassPrice = economyClassPrice;
    }
    public void setEconomyClassPrice(float economyClassPrice) {
        this.economyClassPrice = Float.toString(economyClassPrice);
    }
    public void setBussniessClassPrice(String bussniessClassPrice) {
        this.bussniessClassPrice = bussniessClassPrice;
    }
    public void setBussniessClassPrice(float bussniessClassPrice) {
        this.bussniessClassPrice = Float.toString(bussniessClassPrice);
    }
    public void setFirstClassPrice(String firstClassPrice) {
        this.firstClassPrice = firstClassPrice;
    }
    public void setFirstClassPrice(float firstClassPrice) {
        this.firstClassPrice = Float.toString(firstClassPrice);
    }
    public void adjustPricesWithRatio(String price){
        this.economyClassPrice = price;
        this.bussniessClassPrice = Float.toString(Float.parseFloat(price) * 2);
        this.firstClassPrice = Float.toString(Float.parseFloat(price) * 5);
    }
    public void adjustPricesWithRatio(float price){
        this.economyClassPrice = Float.toString(price);
        this.bussniessClassPrice = Float.toString(price * 2);
        this.firstClassPrice = Float.toString(price * 5);
    }
    public String getEconomyClassPrice() {
        return this.economyClassPrice;
    }
    public String getBussniessClassPrice() {
        return this.bussniessClassPrice;
    }
    public String getFirstClassPrice() {
        return this.firstClassPrice;
    }
}