//You can use break with a label for the outer loop. For example:

public class Test {
  public static void main(String[] args) {
    outerloop:
    for (int i=0; i < 5; i++) {
      for (int j=0; j < 5; j++) {
        if (i * j > 6) {
          System.out.println("Breaking");
          break outerloop;
        }
        System.out.println(i + " " + j);
      }
    }
    System.out.println("Done");
  }
}