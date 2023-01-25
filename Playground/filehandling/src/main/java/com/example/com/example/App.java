package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App 
{
  public static void main(String[] args) {
    System.out.println("file handling demo");
  }

  public static void createFile(String filename){
    try {
      File myFile = new File(filename);
      if (myFile.createNewFile()) {
        System.out.printf("file %s created on %s%n", myFile.getName(), myFile.getAbsolutePath());
      } else {
        System.out.printf("fle already exist on %s%n", myFile.getAbsolutePath());
      }
    } catch (IOException e) {
      System.out.println("An error occurred");
      e.printStackTrace();
    }
  }

  public static void writeToFile(String filename, String inside){
    try {
      FileWriter myFile = new FileWriter(filename);
      myFile.write(inside);
      myFile.close();
      System.out.printf("writed to %s%n",  filename);
    } catch (IOException e) {
      System.out.println("An error occurred");
      e.printStackTrace();
    }
  }

  public static void readFile(String filename){
    try {
      File myFile = new File(filename);
      Scanner scan = new Scanner(myFile);
      while (scan.hasNextLine()) {
        System.out.println(scan.nextLine());
      }
      scan.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred");
      e.printStackTrace();
    }
  }
}
