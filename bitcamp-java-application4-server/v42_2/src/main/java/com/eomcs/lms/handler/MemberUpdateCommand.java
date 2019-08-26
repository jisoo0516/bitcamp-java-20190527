package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberUpdateCommand implements Command {

  private MemberDao memberDao;


  public MemberUpdateCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }


  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호?");
      Member member = memberDao.findBy(no);

      if (member == null) {
        System.out.println("해당 학생을 찾을 수 없습니다.");
        return;
      }
      String str = Input.getStringValue(in, out, "이름");
      if (str.length() > 0) {
        member.setName(str);
      }
      str = Input.getStringValue(in, out, "메일?");
      if (str.length() > 0) {
        member.setEmail(str);
      }

      member.setPassword(Input.getStringValue(in, out, "패스워드 "));

      str = Input.getStringValue(in, out, "사진?");
      if (str.length() > 0) {
        member.setPicture(str);
      }

      str = Input.getStringValue(in, out, "폰번호?");
      if (str.length() > 0) {
        member.setPhoneNum(str);

        memberDao.update(member);
        out.println("데이터를 수정하였습니다.");
      } else {
        out.println("데이터 변경을 취소합니다.");
      }


    } catch (Exception e) {
      out.println("데이터 변경 실패");
      System.out.println(e.getMessage());
    }

  }



}
