package com.eomcs.test.ex1;

public class Calculator {
  static int result = 0;
  
  public static void plus(int value) { // 스태틱 메서드는 JVM stack에 this라는 내장변수가 안 만들어진다.
    result += value;
    
  }
  
  public static void minus(int value) {
    result -= value;
  }
}
