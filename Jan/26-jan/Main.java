import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    int[] arr = {9,8,7,6,4,5,3,2,1};
    int[] arrs = {9,8,7,6,4,5,3,2,1};

    //find number 7
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == 7)
        System.out.printf("Number 7 found in arr[%d]\n", i);
    }

    // print member of arr
    for (int i : arr)
      System.out.print(i + " ");

    System.out.println("");
    
    // bassic sorting algorithm
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[i])
          swapMember(arr,i,j);
      }
      System.out.print(arr[i] + " ");
    }
    System.out.println("");

    System.out.println("======================");

    for (int i : arrs)
      System.out.print(i + " ");

    System.out.println("");
    Arrays.sort(arrs);

    for (int i : arrs)
      System.out.print(i + " ");

      System.out.println("");

      System.out.printf("Number 7 found in arr[%s]\n",Arrays.binarySearch(arrs, 7));
  }

  public static void swapMember(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
