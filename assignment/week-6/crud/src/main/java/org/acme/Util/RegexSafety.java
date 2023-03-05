package org.acme.Util;

import java.util.regex.Pattern;

public class RegexSafety {
    
    private static final String URL_REGEX =
            "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$";
 
    public static boolean isValidUrl(String url) {
        Pattern pattern = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(url).matches();
    }
}
