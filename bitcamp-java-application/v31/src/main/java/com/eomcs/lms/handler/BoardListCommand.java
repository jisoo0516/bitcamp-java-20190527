package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardListCommand implements Command{
  
  private List<Board> list;
  private Input input;
  
 
  public BoardListCommand(Input input, List<Board> list) {
    this.input = input;
    this.list = list;
    
  }
  
 
  @Override
  public void execute() {
    //Board[] boards = new Board[boardList.size()]; //2번 방법
    //boardList.toArray(boards); //2번 방법
    
    Board[] boards =  list.toArray(new Board[] {});//무조건 빈 배열을 만들고 새 배열을 만들어 리턴... 1번방법
    for (Board board : boards) {
      System.out.printf("%s, %s, %s, %s\n", board.getNo(), board.getContents(), board.getReportingDate(),
          board.getHits());
    }
  }

}
