package com.eomcs.lms.handler;
import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.util.Component;
import com.eomcs.util.Input;
import com.eomcs.util.RequestMapping;

@Component
public class CalculatorCommand {
  @RequestMapping("/calc/plus") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void plus(BufferedReader in, PrintStream out) {

    try {
      int a = Input.getIntValue(in, out, "값1?");
      int b = Input.getIntValue(in, out, "값2?");
      
      out.printf("%d + %d = %d\n", a,b,(a+b));
     

    } catch (Exception e) {
      out.println("데이터 저장에 실패");
      System.out.println(e.getMessage());
    }

  }
  
  @RequestMapping("/calc/minus") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void minus(BufferedReader in, PrintStream out) {

    try {
      int a = Input.getIntValue(in, out, "값1?");
      int b = Input.getIntValue(in, out, "값2?");
      
      out.printf("%d - %d = %d\n", a,b,(a-b));
     

    } catch (Exception e) {
      out.println("데이터 저장에 실패");
      System.out.println(e.getMessage());
    }
  }
}

// 실습 과제 :
// => 다음과 같이 실행하도록 위 클래스를 완성하라!
//
/*
 > /calc/plus
 값1? 100
 값2? 200
 100 + 200 =300
 
 > /calc/minus
 값1? 100
 값2? 200
 100-200=-100
 */
