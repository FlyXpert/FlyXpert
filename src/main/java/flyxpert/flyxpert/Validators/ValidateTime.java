package flyxpert.flyxpert.Validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ValidateTime extends Validator {

    String timePattern = "^(1[0-2]|0?[1-9]):[0-5][0-9](AM|PM)$";
    Pattern pattern = Pattern.compile(timePattern);

    @Override
    public Boolean validateData(String time) {
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }
}