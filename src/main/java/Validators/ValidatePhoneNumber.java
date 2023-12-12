package Validators;

public class ValidatePhoneNumber extends ValidatorAbstract {
    @Override
    public Boolean validateData(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d+") && phoneNumber.length() == 11;
    }
}
