package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardDetailCommand implements Command {

  private BoardDao boardDao;


  public BoardDetailCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  
  public String getCommandName() {
    return "/board/detail";
  }
  @Override
  public void execute(BufferedReader in, PrintStream out) {


    try {
      int no = Input.getIntValue(in, out, "번호?");
      Board board = boardDao.findBy(no);

      if (board == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }
      boardDao.increaseViewCount(no);
      
      out.printf("내용: %s\n", board.getContents());
      out.printf("작성일: %s\n", board.getReportingDate());

    } catch (Exception e) {
      out.println("데이터 조회에 실패");
      System.out.println(e.getMessage());
    }
  }

}
