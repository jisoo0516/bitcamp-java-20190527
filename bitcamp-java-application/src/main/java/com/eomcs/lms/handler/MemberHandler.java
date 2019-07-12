package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.lms.util.Input;

public class MemberHandler {
  
  private MemberList memberList = new MemberList();

  private Input input;
  
  public MemberHandler(Input input) {
    this.input = input;
  }
  
  public void addMember() {

    Member member = new Member();

    member.setNo(input.getIntValue("번호?"));
    member.setName(input.getStringValue("이름?"));
    member.setEmail(input.getStringValue("메일?"));
    member.setPassword(input.getIntValue("암호?"));
    member.setPicture(input.getStringValue("사진?"));
    member.setPhoneNum(input.getIntValue("폰번호?"));
    member.setJoinDate(input.getDateValue("가입일?"));

    memberList.add(member);
    System.out.println("저장하였습니다.");
  }
  
  public void listMember() {
    Member [] members = memberList.toArray();
    for (Member member : members) {
      System.out.printf("%s, %s, %s , %s\n", member.getName(), member.getEmail(), member.getPassword(),
          member.getJoinDate());

    }
  }
  

}
