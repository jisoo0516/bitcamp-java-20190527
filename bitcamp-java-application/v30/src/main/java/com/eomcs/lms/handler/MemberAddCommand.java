package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberAddCommand implements Command {


  private List<Member> list;

  private Input input;

 
  public MemberAddCommand(Input input, List<Member> list) {
    this.input = input;
    this.list = list;
  }
  @Override
  public void execute() {

    Member member = new Member();

    member.setNo(input.getIntValue("번호?"));
    member.setName(input.getStringValue("이름?"));
    member.setEmail(input.getStringValue("메일?"));
    member.setPassword(input.getIntValue("암호?"));
    member.setPicture(input.getStringValue("사진?"));
    member.setPhoneNum(input.getIntValue("폰번호?"));
    member.setJoinDate(input.getDateValue("가입일?"));

    list.add(member);
    System.out.println("저장하였습니다.");
  }



}
