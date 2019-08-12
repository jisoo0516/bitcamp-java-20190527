package com.eomcs.lms.handler;

import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberUpdateCommand implements Command {

  private MemberDao memberDao;

  private Input input;

  public MemberUpdateCommand(Input input, MemberDao memberDao) {
    this.input = input;
    this.memberDao = memberDao;
  }


  @Override
  public void execute() {
    int no = input.getIntValue("번호?");

    try {
      Member member = memberDao.findBy(no);

      if (member == null) {
        System.out.println("해당 학생을 찾을 수 없습니다.");
        return;
      }
      String str = input.getStringValue("이름");
      if (str.length() > 0) {
        member.setName(str);
      }
      str = input.getStringValue("메일?");
      if (str.length() > 0) {
        member.setEmail(str);
      }

      member.setPassword(input.getIntValue("패스워드 "));

      str = input.getStringValue("사진?");
      if (str.length() > 0) {
        member.setPicture(str);
      }
      
      str = input.getStringValue("폰번호?");
      if (str.length() > 0) {
        member.setPhoneNum(str);
      }
      

      memberDao.update(member);
      System.out.println("데이터를 수정하였습니다.");

    } catch (Exception e) {
      System.out.println("데이터 삭제 실패");
      System.out.println(e.getMessage());
    }

  }



}
