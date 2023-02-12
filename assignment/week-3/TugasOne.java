/* 
 *  Input, Output, Data Variabel & Variable
 * 
 * 1. Buatlah program untuk input nama anda dan
 *    output kalimat "Selamat datang di bootcamp Kawah Edukasi, {namaAnda}"
 * 
 * 2. Buatlah masing masing dua variable untuk
 *    data type short, double, char, dan boolean
 * 
 * 3. Buatlah 3 variable string dengan value masing-masing,
 *    "Saya senang" "belajar" "Java Language", lalu gabungkan dan print hasilnya.
 */

import java.util.Scanner;

public class TugasOne {
    public static void main(String[] args) {

        // ==========================================================
        // * tugas poin pertama dari tugas minggu ke 3 :
        //   Buatlah program untuk input nama anda dan output kalimat
        //   "Selamat datang di bootcamp Kawah Edukasi, {namaAnda}"
        // ==========================================================

        System.out.print("Tolong masukan Nama Anda : ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        input.close();
        System.out.println( "Selamat datang " +
                            "di bootcamp Kawah Edukasi, " +
                            name);

        // =============================================
        // tugas poin kedua dari tugas minggu ke 3
        // * Buatlah masing masing dua variable untuk
        //   data type short, double, char, dan boolean
        // =============================================

        // membuat 2 variable short
        short   var_short_one,  var_short_two;

        // membuat 2 variable double
        double  var_double_one, var_double_two;

        // membuat 2 variable char
        char    var_char_one,   var_char_two;

        // membuat 2 variable boolean
        boolean var_bool_one,   var_bool_two;

        // =======================================================
        // tugas poin ketiga dari tugas minggu ke 3
        // * Buatlah 3 variable string dengan value masing-masing,
        //   "Saya senang" "belajar" "Java Language",
        //   lalu gabungkan dan print hasilnya.
        // =======================================================

        // membuuat String array untuk menampung 3 buah string
        String values[] = { "Saya senang",
                            "belajar",
                            "Java Language"};
        
        // menggabungkan isi dari string array values ke variable baru
        String cont_values = new String();
        for (String val : values)
            cont_values = cont_values.concat(val);
        
        // menampilkan hasil ke standar output
        System.out.println(cont_values);
    }
}
