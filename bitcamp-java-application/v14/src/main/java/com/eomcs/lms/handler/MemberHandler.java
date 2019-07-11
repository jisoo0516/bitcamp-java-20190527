package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.lms.util.Input;

public class MemberHandler {
  private Member[] members = new Member[100];
  private int memberssize = 0;

  private Input input;
  
  public MemberHandler(Input input) {
    this.input = input;
  }
  
  public void addMember() {

    Member member = new Member();

    member.no = input.getIntValue("번호?");
    member.name = input.getStringValue("이름?");
    member.email = input.getStringValue("메일?");
    member.password = input.getIntValue("암호?");
    member.picture = input.getStringValue("사진?");
    member.phoneNum = input.getIntValue("폰번호?");
    member.joinDate = input.getDateValue("가입일?");

    members[memberssize++] = member;
    System.out.println("저장하였습니다.");
  }
  
  public void listMember() {
    for (int i = 0; i < memberssize; i++) {
      Member member = members[i];
      System.out.printf("%s, %s, %s , %s\n", member.name, member.email, member.password,
          member.joinDate);

    }
  }
  

}
