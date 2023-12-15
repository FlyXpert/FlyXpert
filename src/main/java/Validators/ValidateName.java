package Validators;

public class ValidateName extends ValidatorAbstract{
    @Override
    public Boolean validateData(String name) {
        return name != null && name.matches("^[a-zA-Z]*$");
    }
}