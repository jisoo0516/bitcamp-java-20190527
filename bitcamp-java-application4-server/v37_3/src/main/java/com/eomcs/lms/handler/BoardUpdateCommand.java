package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardUpdateCommand implements Command {

  private BoardDao boardDao;


  public BoardUpdateCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }


  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호?");

      Board board = boardDao.findBy(no);
      if (board == null) {
        out.println("해당 수업을 찾을 수 없습니다.");
        return;
      }

      String str = Input.getStringValue(in, out, "내용?");
      if (str.length() > 0) {
        board.setContents(str);
        boardDao.update(board);
        out.println("데이터를 수정하였습니다.");

      } else {
        out.println("데이터 변경을 취소합니다.");
      }


    } catch (Exception e) {
      out.println("데이터 수정 실패");
      System.out.println(e.getMessage());
    }
  }

}
