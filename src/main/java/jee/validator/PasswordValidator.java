package jee.validator;

import java.util.regex.Pattern;

public class PasswordValidator {
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    private PasswordValidator() {
        throw new IllegalStateException("Utility class");
    }
    public static boolean isValid(String password) {
        var pattern = Pattern.compile(PASSWORD_PATTERN);
        var matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
