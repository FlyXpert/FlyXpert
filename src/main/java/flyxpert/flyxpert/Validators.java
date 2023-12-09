package flyxpert.flyxpert;

import javax.xml.validation.Validator;

public class Validators extends AbstractValidator {
    public static boolean validateName(String name) {
        return name != null && name.matches("^[a-zA-Z]*$");
    }
    public static Boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d+") && phoneNumber.length() == 11;
    }
}
