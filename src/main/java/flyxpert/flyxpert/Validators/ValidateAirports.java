package flyxpert.flyxpert.Validators;
public class ValidateAirports extends Validator {
    public Boolean validateData(String name) {
        return name != null && name.matches("^[a-zA-Z]*$") && name.length() == 3;
    }
}