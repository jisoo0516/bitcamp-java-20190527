package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardListCommand implements Command {

  private BoardDao boardDao;


  public BoardListCommand(BoardDao boardDao) {
    this.boardDao = boardDao;

  }


  @Override
  public void execute(BufferedReader in, PrintStream out) {
    try {

      List<Board> boards = boardDao.findAll();
      for (Board board : boards) {
        out.printf("%s, %s, %s, %s\n",
            board.getNo(), board.getContents(), board.getReportingDate(),
            board.getHits());
      }
    } catch (Exception e) {
      out.println("데이터 목록조회 실패");
      System.out.println(e.getMessage());
    }
  }

}