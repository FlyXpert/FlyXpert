package flyxpert.flyxpert;

public class Airport {
    private String code;
    private String name;
    private String location;
    public Airport(String code, String name, String location){
        setCode(code);
        setName(name);
        setLocation(location);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getLocation() {
        return location;
    }
}
