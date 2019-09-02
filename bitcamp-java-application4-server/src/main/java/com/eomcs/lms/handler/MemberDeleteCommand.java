package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.util.Component;
import com.eomcs.util.Input;
import com.eomcs.util.RequestMapping;
@Component("/member/delete")
public class MemberDeleteCommand {

  private MemberDao memberDao;


  public MemberDeleteCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  
  @RequestMapping // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void execute(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호?");

      if (memberDao.delete(no) > 0) {
        out.println("데이터 삭제");
      } else {
        out.println("해당 데이터 없음");
      }

    } catch (Exception e) {
      out.println("데이터 삭제 실패");
      System.out.println(e.getMessage());
    }

  }

}


