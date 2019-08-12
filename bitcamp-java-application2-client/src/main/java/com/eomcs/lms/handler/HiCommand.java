package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.util.Input;

public class HiCommand implements Command {
 
  private Input input;

  public HiCommand(Input input) {
    this.input = input;
    
  }
  @Override
  public void execute() {
//    System.out.println("이름?");
//    
//    Scanner sc = new Scanner(System.in);
//    String a =sc.nextLine();
//    if(!(a.equals(null))) {
//      System.out.println("안녕하세요,"+a+"님!");
//    }
  
    String name = input.getStringValue("이름?");
    System.out.println("안녕하세요," + name+"님!");
   
    
  }

}
