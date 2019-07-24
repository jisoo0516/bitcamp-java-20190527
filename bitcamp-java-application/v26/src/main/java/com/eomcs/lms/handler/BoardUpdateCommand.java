package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardUpdateCommand implements Command {
  
  private List<Board> list;
  private Input input;
  
 
  public BoardUpdateCommand(Input input, List<Board> list) {
    this.input = input;
    this.list = list;
    
  }
  
 
  @Override
  public void execute() {
    int no = input.getIntValue("번호?");

    // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Lesson 객체를 찾는다.
    Board board = null;
    for (int i = 0; i < list.size(); i++) {
      Board temp = list.get(i);
      if (temp.getNo() == no) {
        board = temp;
        break;
      }
    }

    if (board == null) {
      System.out.println("해당 수업을 찾을 수 없습니다.");
      return;
    }


    // 사용자로부터 변경할 값을 입력받는다.
    String str = input.getStringValue("내용(" + board.getContents() + ")?");
    if (str.length() > 0) {
      board.setContents(str);

    }
  
    board.setReportingDate(input.getDateValue("작성일(" + board.getReportingDate() + ")? "));
    
    board.setHits(input.getIntValue("조회수(" + board.getHits() + ")? "));

  
    
    System.out.println("데이터를 수정하였습니다.");
    
  }
 
}
