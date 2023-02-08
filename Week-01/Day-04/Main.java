import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    String fileName = "newFile.txt";
    String fileWrite = "Hello World";

    try {
      // create new file
      File newFile = new File(fileName);
      if (newFile.createNewFile()) {
        System.out.printf("%s created\n", newFile.getName());
      } else {
        System.out.printf("%s already creted\n", newFile.getName());
      }

      // write file
      FileWriter writeFile = new FileWriter(fileName);
      writeFile.write(fileWrite);
      writeFile.close();
      System.out.println("Write \"" + fileWrite + "\" to file " + newFile.getName());

      // read file
      try {
        Scanner readFile = new Scanner(newFile);
        System.out.println("Read " + newFile.getName());
        while (readFile.hasNextLine()) {
          System.out.println(readFile.nextLine());
        }
        readFile.close();
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred");
        e.printStackTrace();
      }
    } catch (IOException e) {
      System.out.println("An error occurred");
      e.printStackTrace();
    }
  }
}