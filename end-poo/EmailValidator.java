import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Serializable;

public class EmailValidator implements Serializable {

    private static final String EMAIL_PATTERN =
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);


    /**
     * Validate mail with regular expression
     *
     * @param mail Mail to test if valid
     * @return true valid mail, false invalid mail
     */
    public static boolean validate (String mail) {
        Matcher matcher;
        matcher = pattern.matcher(mail);
        return matcher.matches();
    }
}