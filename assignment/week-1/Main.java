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

      printMenu();

      // inisialisasi variable
      int numMenu = inputUser.nextInt();
      int bilPertama, bilKedua;

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
      
      try {
        // inisialisasi untuk file log
        FileWriter log = new FileWriter("result.txt");
        // pattern match menu apa yang dipilih
        switch (numMenu) {

          // bila operator adalah +
          case 1:
            System.out.printf("hasil dari %d + %d adalah %d \n"
                              , bilPertama
                              , bilKedua
                              , bilPertama + bilKedua);
            log.write("hasil dari " + bilPertama + " + " + bilKedua +" adalah " + bilPertama + bilKedua);
            log.close();
            break;

          // bila operator adalah -
          case 2:
            System.out.printf("hasil dari %d - %d adalah %d \n"
                              , bilPertama
                              , bilKedua
                              , bilPertama - bilKedua);
            log.write("hasil dari " + bilPertama + " - " + bilKedua +" adalah " + bilPertama + bilKedua);
            log.close();
            break;

          // bila operator adalah *
          case 3:
            System.out.printf("hasil dari %d * %d adalah %d \n"
                              , bilPertama
                              , bilKedua
                              , bilPertama * bilKedua);
            log.write("hasil dari " + bilPertama + " * " + bilKedua +" adalah " + bilPertama + bilKedua);
            log.close();
            break;

          // bila operator adalah /
          case 4:
            // untuk mengecek apakah yang dibagi adalah 0
            if (bilKedua == 0) {
              System.out.println("tidak bisa dibagi dengan 0");
            } else {
              System.out.printf("hasil dari %d / %d adalah %d \n"
                                , bilPertama
                                , bilKedua
                                , bilPertama / bilKedua);
              log.write("hasil dari " + bilPertama + " / " + bilKedua +" adalah " + bilPertama + bilKedua);
              log.close();
            }
            break;

          // bila operator adalah %
          case 5:
            System.out.printf("hasil dari %d %% %d adalah %d \n"
                              , bilPertama
                              , bilKedua
                              , bilPertama % bilKedua);
            log.write("hasil dari " + bilPertama + " % " + bilKedua +" adalah " + bilPertama + bilKedua);
            log.close();
            break;
        
          default:
            System.out.println("terjadi error saat pattern matching");
            log.write("error saat pattern match");
            log.close();
            break;
        }
      } catch (IOException e) {
        System.out.println("error saat membuka file");
        e.printStackTrace();
      }

    } catch (InputMismatchException e) {
      System.out.println("harap masukan angka");
      e.printStackTrace();
    }
  }

  public static void printMenu(){
    System.out.println("Calculator Menu :");
    System.out.println("1. penjumlahan");
    System.out.println("2. pengurangan");
    System.out.println("3. perkalian");
    System.out.println("4. pembagian");
    System.out.println("5. sisa bagi");
    System.out.print("Silakan masukan nomor : ");
  }

}
