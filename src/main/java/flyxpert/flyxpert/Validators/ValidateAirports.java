package flyxpert.flyxpert.Validators;
public class ValidateAirports extends ValidatorAbstract{
    @Override
    public Boolean validateData(String name) {
        return name != null && name.matches("^[a-zA-Z]*$") && name.length() == 3;
    }
}