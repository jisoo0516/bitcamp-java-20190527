package com.eomcs.lms.handler;

import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardDetailCommand implements Command {

  private BoardDao boardDao;
  private Input input;


  public BoardDetailCommand(Input input, BoardDao boardDao) {
    this.input = input;
    this.boardDao = boardDao;

  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호?");

    try {
      Board board = boardDao.findBy(no);

      if (board == null) {
        System.out.println("해당 번호의 데이터가 없습니다!");
        return;
      }

      System.out.printf("내용: %s\n", board.getContents());
      System.out.printf("조회수: %s\n", board.getHits());
      System.out.printf("작성일: %s\n", board.getReportingDate());

    } catch (Exception e) {
      System.out.println("데이터 조회에 실패");
      System.out.println(e.getMessage());
    }
  }

}
