/* 1. Regular Expression
 *    Buatlah method yang untuk mengecek pattern pada string apakah bertipe email,
 *    pattern Tanggal (dd-MM-yyyy,)  Pattern Tanggal  (dddd, MMMM yy) atau IP Address.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TugasOne {
    public static void main(String[] args) {
        // melakukan pengecekan jika fungsi bisa berjalan
        whatIsThis("muhammadaviv14@gmail.com");
        whatIsThis("19-01-2038");
        whatIsThis("Jumat, January 38");
        whatIsThis("127.0.0.1");

        // mengecek juga bila input bukan termasuk
        // input yang diharapkan dan akan mencentak "NONE"
        // ke standard output
        whatIsThis("c programming");
    }

    public static boolean whatIsThis(String input) {

        // membuat array yang berisi regex yang akan diterapkan
        // dalam string
        final String pattern[] = {
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", // email
            "^(0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-\\d{4}$", // date DD-MM-YYYY
            "^[A-Z][a-z]{3,8}, [A-Z][a-z]{2,8} \\d{2}$", // date dddd, MMMM YY
            "\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b" // Ip addr
        };

        // mendeklarasikan object dengan fungsi regex
        // dan memberikan null
        Pattern regex = null;
        Matcher matcher = null;

        // untuk mengecek setiap regex
        for (int i = 0;i < pattern.length;i++) {
            switch (i) {
                // pattern pertama dalah email
                case 0:
                    regex = Pattern.compile(pattern[i]);
                    matcher = regex.matcher(input);
                    if(matcher.find()){
                        System.out.println("Pattern Matched : This is Email");
                        return true;
                    }
                    break;

                // pattern kedua adalah date dd-MM-yyyy
                case 1:
                    regex = Pattern.compile(pattern[i]);
                    matcher = regex.matcher(input);
                    if(matcher.find()){
                        System.out.println("Pattern Matched : This is Date DD-MM-YYYY");
                        return true;
                    }
                    break;

                // pattern ketiga adalah date dd, MM yyyy
                case 2:
                    regex = Pattern.compile(pattern[i]);
                    matcher = regex.matcher(input);
                    if(matcher.find()){
                        System.out.println("Pattern Matched : This is Date dddd, MMMM YY");
                        return true;
                    }
                    break;

                // pattern keempat adalah ip addr
                case 3:
                    regex = Pattern.compile(pattern[i]);
                    matcher = regex.matcher(input);
                    if(matcher.find()){
                        System.out.println("Pattern Matched : This is IP Address");
                        return true;
                    }
                    break;
            }

        }

        // akan tercetak "NONE" dan return false
        System.out.println("NONE");
        return false;
    }
}
