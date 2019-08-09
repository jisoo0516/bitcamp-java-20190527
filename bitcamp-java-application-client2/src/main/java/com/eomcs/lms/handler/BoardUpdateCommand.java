package com.eomcs.lms.handler;

import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardUpdateCommand implements Command {

  private BoardDao boardDao;
  private Input input;


  public BoardUpdateCommand(Input input, BoardDao boardDao) {
    this.input = input;
    this.boardDao = boardDao;
  }


  @Override
  public void execute() {
    int no = input.getIntValue("번호?");

    try {
      Board board = boardDao.findBy(no);
      if (board == null) {
        System.out.println("해당 수업을 찾을 수 없습니다.");
        return;
      }

      String str = input.getStringValue("내용");
      if (str.length() > 0) {
        board.setContents(str);
      }

      boardDao.update(board);
      System.out.println("데이터를 수정하였습니다.");

    } catch (Exception e) {
      System.out.println("데이터 삭제 실패");
      System.out.println(e.getMessage());
    }
  }

}
