public class test {
  public static void main(String[] args) {
    System.out.println(addTwo(addOne(1)));
  }
  public static int addOne(int x){
    return x + 1;
  }
  public static int addTwo(int x){
    return x + 2;
  }
}
