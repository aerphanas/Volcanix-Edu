import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class Main {
  public static void main(String[] args) {
    // exception with try catch
    try {
      System.out.println("Hello World");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // user input exception
    try {
      System.out.print("Masukan input : ");
      Scanner in = new Scanner(System.in);
      int input = in.nextInt();
      in.close();
      System.out.println("Anda menginput : " + input);
    } catch (InputMismatchException e) {
      System.out.println("Tolong masukan angka");
    }
  }

  // input output exception
  try {
    File myfile = new File("test.txt");
    if (myfile.createNewFile()) {
      System.out.println("file " + file.getName + " berhasil dibuat");
    } else {
      System.out.println("file gagal dibuat");
    }
  } catch (IOException e) {
    System.out.println("file tidak ada");
  }
}
