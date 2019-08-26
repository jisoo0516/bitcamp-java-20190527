package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberAddCommand implements Command {


  private MemberDao memberDao;



  public MemberAddCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {


    try {
      Member member = new Member();

      member.setName(Input.getStringValue(in, out, "이름?"));
      member.setEmail(Input.getStringValue(in, out, "메일?"));
      member.setPassword(Input.getIntValue(in, out, "암호?"));
      member.setPicture(Input.getStringValue(in, out, "사진?"));
      member.setPhoneNum(Input.getStringValue(in, out, "폰번호?"));
      memberDao.insert(member);

      out.println("저장하였습니다.");
    } catch (Exception e) {
      out.println("데이터 저장에 실패");
      System.out.println(e.getMessage());
    }
  }



}
