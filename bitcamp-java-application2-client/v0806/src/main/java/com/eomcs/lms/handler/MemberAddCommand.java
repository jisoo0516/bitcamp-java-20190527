package com.eomcs.lms.handler;

import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberAddCommand implements Command {


  private MemberDao memberDao;

  private Input input;


  public MemberAddCommand(Input input, MemberDao memberDao) {
    this.input = input;
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {

    Member member = new Member();

    member.setNo(input.getIntValue("번호?"));
    member.setName(input.getStringValue("이름?"));
    member.setEmail(input.getStringValue("메일?"));
    member.setPassword(input.getIntValue("암호?"));
    member.setPicture(input.getStringValue("사진?"));
    member.setPhoneNum(input.getStringValue("폰번호?"));
    member.setJoinDate(input.getDateValue("가입일?"));

    try {
      memberDao.insert(member);
      System.out.println("저장하였습니다.");
    } catch (Exception e) {
      System.out.println("데이터 저장에 실패");
      System.out.println(e.getMessage());
    }
  }



}
