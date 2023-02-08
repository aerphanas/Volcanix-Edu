public class Main {
  public static void main(String []args){
    /* This is
     * multi line
     * comment
     */
    // single line coment

    // primitive type :
    int xPrim = 1;
    char yPrim = 'A';
    float zPrim = 5.99f;
    boolean aPrim = true;

    // Object type :
    Integer xOb = 1;
    Character yOb = 'A';
    Float zOb = 5.99f;
    Boolean aOb = true;

    // String object :
    String oString = "Hello";


    // control flow
    if (1 < 2) {
      System.out.println("2 is greater than 1");
    }

    if (1 > 2) {
      System.out.println("1 is greater than 2");
    } else {
      System.out.println("2 is greater than 1");
    }

    switch (xPrim) {
      case 1:
        System.out.println("value xPrim is 1");
        break;
      case 2 :
        System.out.println("value xPrim is 2");
        break;
      default:
        System.out.println("xPrim doesn't have value");
        break;
    }

    // Arithmetic Operators
    /*
    + 	Addition 	Adds together two values 	x + y 	
    - 	Subtraction 	Subtracts one value from another 	x - y 	
    * 	Multiplication 	Multiplies two values 	x * y 	
    / 	Division 	Divides one value by another 	x / y 	
    % 	Modulus 	Returns the division remainder 	x % y 	
    ++ 	Increment 	Increases the value of a variable by 1 	++x 	
    -- 	Decrement 	Decreases the value of a variable by 1 	--x
    */
    System.out.printf("1 + 1  = %d\n", 1+1);
    System.out.printf("1 - 1  = %d\n", 1-1);
    System.out.printf("1 * 1  = %d\n", 1*1);
    System.out.printf("1 / 1  = %d\n", 1/1);
    System.out.printf("5 %% 2 = %d\n", 5%2);
    System.out.printf("1++    = %d\n", 1 + 1);
    System.out.printf("1--    = %d\n", 1 - 1);
  }
}