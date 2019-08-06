package com.eomcs.lms.handler;

import com.eomcs.util.Input;

public class PlusCommand implements Command{
  
  private Input input;
  
  public PlusCommand(Input input) {
    this.input= input;
  }

  @Override
  public void execute() {
   int a =input.getIntValue("값1?");
   int b = input.getIntValue("값2?");
   
   System.out.println("합계는"+(a+b)+"입니다.");
    
  }
  
  

}
