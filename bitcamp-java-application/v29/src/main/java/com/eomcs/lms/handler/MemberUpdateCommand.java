package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberUpdateCommand implements Command {

  private List<Member> list;

  private Input input;

  public MemberUpdateCommand(Input input, List<Member> list) {
    this.input = input;
    this.list = list;
  }


   @Override
  public void execute() {
    int no = input.getIntValue("번호?");

    Member member = null;
    for (int i = 0; i < list.size(); i++) {
      Member temp = list.get(i);
      if (temp.getNo() == no) {
        member = temp;
        break;
      }
    }
    if (member == null) {
      System.out.println("해당 학생을 찾을 수 없습니다.");
      return;
    }
    String str = input.getStringValue("이름:(" + member.getName() + ")?");
    if (str.length() > 0) {
      member.setName(str);
    }
    str = input.getStringValue("메일?");
    if (str.length() > 0) {
      member.setEmail(str);
    }

    member.setPassword(input.getIntValue("패스워드(" + member.getPassword() + ")? "));

    str = input.getStringValue("사진?");
    if (str.length() > 0) {
      member.setPicture(str);
    }
    member.setPhoneNum(input.getIntValue("폰번호(" + member.getPhoneNum() + ")? "));

    member.setJoinDate(input.getDateValue("시작일(" + member.getJoinDate() + ")? "));

  }



  



}
