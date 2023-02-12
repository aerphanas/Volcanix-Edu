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

        // memberikan value untuk variable yang dibuat

        // memberikan value ke tipe data short
        var_short_one = 0;
        var_short_two = 0;

        // memberikan value ke tipe data double
        var_double_one = 0.0;
        var_double_two = 0.0;

        // memberikan value ke tipe data char
        var_char_one = '0';
        var_char_two = '0';

        // memberikan value ke tipe data boolean
        var_bool_one = false;
        var_bool_two = false;

        // =======================================================
        // tugas poin ketiga dari tugas minggu ke 3
        // * Buatlah 3 variable string dengan value masing-masing,
        //   "Saya senang" "belajar" "Java Language",
        //   lalu gabungkan dan print hasilnya.
        // =======================================================

        // membuat 3 variable string dengan variable yang ditentukan
        String var_one = "Saya senang";
        String var_two = "belajar";
        String var_three = "Java Language";

        // menggabungkan string ke dalam satu variable
        String gabungan = new String();
        gabungan = gabungan.concat(var_one);
        gabungan = gabungan.concat(var_two);
        gabungan = gabungan.concat(var_three);

        // menampilkan hasil ke standar output
        System.out.println(gabungan);
    }
}
