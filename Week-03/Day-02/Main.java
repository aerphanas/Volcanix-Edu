public class Main {
  public static void main(String[] args) {
    String name = new String("muhammad");
    name = name.concat(" aviv ").concat("b      ").toUpperCase();
    int panjangName = name.length();
    String nameWithoutEndSpace = name.stripTrailing();
    System.out.println(nameWithoutEndSpace);
  }
}
