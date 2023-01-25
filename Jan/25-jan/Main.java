public class Main {
  public static void main(String[] args) {
    
    // array
    
    int[] age = {1,2,3,4,5};
    
    // pointer (menggambil anggota array)
    System.out.println("anggota ke empat dari array age adalah " + age[4]);

    // while loop
    int i = 0;
    while (age.length > i) {
      System.out.println("isi array dari age " + age[i]);
      i++;
    }
    i = 0;

    newline();

    // do while loop
    do {
      System.out.println("isi array dari age " + age[i]);
      i++;
    } while (age.length > i);
    

    newline();

    // for loop
    for (int j = 0; j < age.length; j++) {
      System.out.println("isi array dari age " + age[j]);
    }

    newline();

    // foreach loop
    for (int n : age)
    System.out.println("isi array dari age " + n);

  }

  // function
  public static void newline(){
    System.out.println("");
  }
}
