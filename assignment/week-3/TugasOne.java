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
        short varShortOne, varShortTwo;

        // membuat 2 variable double
        double varDoubleOne, varDoubleTwo;

        // membuat 2 variable char
        char varCharOne, varCharTwo;

        // membuat 2 variable boolean
        boolean varBoolOne, varBoolTwo;

        // memberikan value untuk variable yang dibuat

        // memberikan value ke tipe data short
        varShortOne = 0;
        varShortTwo = 0;

        // memberikan value ke tipe data double
        varDoubleOne = 0.0;
        varDoubleTwo = 0.0;

        // memberikan value ke tipe data char
        varCharOne = '0';
        varCharTwo = '0';

        // memberikan value ke tipe data boolean
        varBoolOne = false;
        varBoolTwo = false;

        // =======================================================
        // tugas poin ketiga dari tugas minggu ke 3
        // * Buatlah 3 variable string dengan value masing-masing,
        //   "Saya senang" "belajar" "Java Language",
        //   lalu gabungkan dan print hasilnya.
        // =======================================================

        // membuat 3 variable string dengan variable yang ditentukan
        String varOne = "Saya senang";
        String varTwo = "belajar";
        String varThree = "Java Language";

        // menggabungkan string ke dalam satu variable
        String mixStrings = varOne +
                            varTwo +
                            varThree;

        // menampilkan hasil ke standar output
        System.out.println(mixStrings);
    }
}
