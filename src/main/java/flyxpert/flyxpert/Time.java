package flyxpert.flyxpert;

public class Time {
    private String hour;
    private String minutes;

    public Time(String hour,String minutes){
        this.hour = hour;
        this.minutes = minutes;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }
}
