package ch10;

public class Test {
  public static void main(String[] args) {
    m1(2,3);
    m1(2,3,4);
    m1(2,3);
    m1(2);
  }

  private static void m1(int... k) {
    System.out.println("2");
    
  }
  
//  private static void m1(int i, int j) {
//    System.out.println("1");
//    
//  }
  
}


