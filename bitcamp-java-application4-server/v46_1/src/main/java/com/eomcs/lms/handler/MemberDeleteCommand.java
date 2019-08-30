package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.util.Input;

public class MemberDeleteCommand implements Command {

  private MemberDao memberDao;


  public MemberDeleteCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  public String getCommandName() {
    return "/member/delete";
  }
  @Override
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


