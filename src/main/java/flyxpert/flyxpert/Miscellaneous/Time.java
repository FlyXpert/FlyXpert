package flyxpert.flyxpert.Miscellaneous;

public class Time {
    private String hour;
    private String minutes;
    private String period;

    public Time(String hour,String minutes,String period){
        this.hour = hour;
        this.minutes = minutes;
        this.period = period;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }


    @Override
    public String toString() {
        return (hour + ":" + minutes + " " + period);
    }
}
