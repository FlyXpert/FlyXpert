package flyxpert.flyxpert.Validators;

public class ValidateName extends Validator {
    @Override
    public Boolean validateData(String name) {
        return name != null && name.matches("[a-zA-Z ,.-]+");
    }
}