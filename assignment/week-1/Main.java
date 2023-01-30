/* Buatlah sebuah kalkulator sederhana yang memenuhi ketentuan-ketentuan berikut:
 * 1. Saat program dijalankan, user dapat memilih menu
 *    penjumlahan, pengurangan, perkalian, pembagian, dan sisa bagi.
 * 
 * 2. Kemudian user akan diminta untuk memasukkan dua buah bilangan,
 *    lalu akan tercetak ke layar hasil dari operasi dua bilangan tersebut sesuai menu yang dipilih
 *    dan setelahnya akan menyimpan hasil dalam bentuk result.txt
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    
    // untuk menyaring input hanya untuk tipe int
    try (Scanner inputUser = new Scanner(System.in)) {

      // menampilkan menu
      printMenu();

      // inisialisasi variable
      int numMenu = inputUser.nextInt();
      int bilPertama, bilKedua;
      String fileName = "result.txt";
      String hasil = null;

      // bila input ada yang salah maka program akan
      // menanyakan nomor sampai benar
      while (numMenu > 5 || numMenu == 0 ) {
        printMenu();
        numMenu = inputUser.nextInt();
      }
      
      // mengambil bilangan
      System.out.print("Masukan bilangan pertama : ");
      bilPertama = inputUser.nextInt();
      System.out.print("Masukan bilangan kedua   : ");
      bilKedua = inputUser.nextInt();
      
      // pattern match menu apa yang dipilih
      switch (numMenu) {

        // bila operator adalah +
        case 1:
          hasil = result(bilPertama, bilKedua, '+');
          System.out.println(hasil);
          writeToFile(fileName, hasil);
          break;

        // bila operator adalah -
        case 2:
          hasil = result(bilPertama, bilKedua, '-');
          System.out.println(hasil);
          writeToFile(fileName, hasil);
          break;

        // bila operator adalah *
        case 3:
          hasil = result(bilPertama, bilKedua, '*');
          System.out.println(hasil);
          writeToFile(fileName, hasil);
          break;

        // bila operator adalah /
        case 4:
          // untuk mengecek apakah yang dibagi adalah 0
          if (bilKedua == 0)
            System.out.println("Error : Tidak bisa dibagi dengan 0");
          else {
            hasil = result(bilPertama, bilKedua, '/');
            System.out.println(hasil);
            writeToFile(fileName, hasil);
          }
          break;

        // bila operator adalah %
        case 5:
          hasil = result(bilPertama, bilKedua, '%');
          System.out.println(hasil);
          writeToFile(fileName, hasil);
          break;
      
        // untuk berjaga-jaga
        default:
          System.out.println("Error : Tolong jalankan ulang");
          break;
      }

    } catch (InputMismatchException e) {
      System.out.println("Error : Tolong masukan angka");
      e.printStackTrace();
    }
  }

  // fungsi menu yang akan ditampilkan 
  // saat program berjalan pertamakali
  public static void printMenu(){
    System.out.println("Calculator Menu :");
    System.out.println("1. Penjumlahan");
    System.out.println("2. Pengurangan");
    System.out.println("3. Perkalian");
    System.out.println("4. Pembagian");
    System.out.println("5. Sisa bagi");
    System.out.print("Silakan Masukan Nomor : ");
  }

  // agar tidak mengulang menulis kode
  // maka saya buat sebuah fungsi
  public static String result(int bilPertama, int bilKedua, char operasi){
    int penjumlahan = 0;
    switch (operasi) {
      case '+':
        penjumlahan = bilPertama + bilKedua;
        break;
      case '-':
        penjumlahan = bilPertama - bilKedua;
        break;
      case '*':
        penjumlahan = bilPertama * bilKedua;
        break;
      case '/':
        penjumlahan = bilPertama / bilKedua;
        break;
      case '%':
        penjumlahan = bilPertama % bilKedua;
        break;
    }
    return "hasil dari "
          + bilPertama
          + " " + operasi + " "
          + bilKedua
          + " adalah "
          + penjumlahan;
  }

  // fungsi untuk menulis output ke file
  public static void writeToFile(String filename, String inside){
    try {
      FileWriter myFile = new FileWriter(filename);
      myFile.write(inside);
      myFile.close();
    } catch (IOException e) {
      System.out.println("Error : Membuat File Gagal");
      e.printStackTrace();
    }
  }

}
