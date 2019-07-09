package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.util.Input;

public class MemberHandler {

  private Member[] members = new Member[100];
  private int memberssize = 0;
  public static Scanner keyScan;
  
  public void addMember() {
    Member member = new Member();
    
    member.no = Input.getIntValue("번호?");
    member.name = Input.getStringValue("이름?");
    member.email = Input.getStringValue("메일?");
    member.password = Input.getIntValue("암호?");
    member.picture = Input.getStringValue("사진?");
    member.phoneNum = Input.getIntValue("폰번호?");
    member.joinDate = Input.getDateValue("가입일?");
    
    members[memberssize++] = member;
    System.out.println("저장하였습니다.");
  }
  
  public void listMember() {
    
    for (int i = 0; i < memberssize; i++) {
      Member member = new Member();
      member = members[i];
      System.out.printf("%s, %s, %s , %s\n", member.name, member.email, member.password,
          member.joinDate);
      
    }
    
  }



}
