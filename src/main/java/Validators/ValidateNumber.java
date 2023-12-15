package Validators;

public class ValidateNumber extends ValidatorAbstract {
    @Override
    public Boolean validateData(String string) {
        return string != null && string.matches("\\d+");
    }
}