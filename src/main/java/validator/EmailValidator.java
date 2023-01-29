package validator;

import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_PATTERN = "^[A-Z0-9._+%&*-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    private EmailValidator() {
        throw new IllegalStateException("Utility class");
    }
    public static boolean isValid(String email) {
        var pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        var matcher = pattern.matcher(email);
        return matcher.matches();
    }
}