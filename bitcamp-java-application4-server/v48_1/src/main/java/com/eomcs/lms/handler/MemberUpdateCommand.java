package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Component;
import com.eomcs.util.Input;
import com.eomcs.util.RequestMapping;
@Component("/member/update")
public class MemberUpdateCommand  {

  private MemberDao memberDao;


  public MemberUpdateCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
 

  @RequestMapping // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void execute(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호?");

      Member member = memberDao.findBy(no);
      if (member == null) {
        System.out.println("해당 학생을 찾을 수 없습니다.");
        return;
      }
      // 사용자로부터 변경할 값을 입력 받는다.
      Member data = new Member();
      data.setNo(no);

      String str = Input.getStringValue(in, out, "이름");
      if (str.length() > 0) {
        data.setName(str);
      }

      str = Input.getStringValue(in, out, "메일?");
      if (str.length() > 0) {
        data.setEmail(str);
      }

      str = Input.getStringValue(in, out, "암호? ");
      if (str.length() > 0) {
        data.setPassword(str);
      }

      str = Input.getStringValue(in, out, "사진?");
      if (str.length() > 0) {
        data.setPicture(str);
      }

      str = Input.getStringValue(in, out, "폰번호?");
      if (str.length() > 0) {
        data.setPhoneNum(str);

      }
      memberDao.update(data);
      out.println("데이터를 수정하였습니다.");


    } catch (Exception e) {
      out.println("데이터 변경 실패");
      System.out.println(e.getMessage());
    }

  }



}
