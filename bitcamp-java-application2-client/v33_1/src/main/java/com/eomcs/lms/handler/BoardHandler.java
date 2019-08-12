package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardHandler {
  
  private List<Board> boardList;
  private Input input;
  
 
  public BoardHandler(Input input, List<Board> list) {
    this.input = input;
    this.boardList = list;
    
  }
  
 
  public void listBoard() {
    //Board[] boards = new Board[boardList.size()]; //2번 방법
    //boardList.toArray(boards); //2번 방법
    
    Board[] boards =  boardList.toArray(new Board[] {});//무조건 빈 배열을 만들고 새 배열을 만들어 리턴... 1번방법
    for (Board board : boards) {
      System.out.printf("%s, %s, %s, %s\n", board.getNo(), board.getContents(), board.getReportingDate(),
          board.getHits());
    }
  }

  public void detailBoard() {
    int no = input.getIntValue("번호?");
    
   Board board = null;
    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
      if (temp.getNo() == no) {
        board = temp;
        break;
      }
    }

    if (board == null) {
      System.out.println("해당 번호의 데이터가 없습니다!");
      return;
    }

    System.out.printf("내용: %s\n", board.getContents());
    System.out.printf("조회수: %s\n", board.getHits());
    System.out.printf("작성일: %s\n", board.getReportingDate());
    
  }

  public void updateBoard() {
    int no = input.getIntValue("번호?");

    // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Lesson 객체를 찾는다.
    Board board = null;
    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
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

  public void deleteBoard() {
    int no = input.getIntValue("번호?");

    // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Lesson 객체를 찾는다.
    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
      if (temp.getNo() == no) {
        boardList.remove(i);
        System.out.println("데이터를 삭제하였습니다");
        return;
      }
    }
    
      System.out.println("해당 수업을 찾을 수 없습니다.");
    
  }
 
}
