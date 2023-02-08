import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    ArrayList<Integer> arr = new ArrayList<Integer>();
    for (int i = 0; i < 10; i++) {
      arr.add(i);
    }
    for (Integer i : arr) {
      System.out.print(i);
    }
  }
}