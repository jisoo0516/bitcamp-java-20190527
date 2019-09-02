package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Component;
import com.eomcs.util.RequestMapping;
@Component("/board/list")
public class BoardListCommand  {

  private BoardDao boardDao;


  public BoardListCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  
  
  @RequestMapping // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
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
      e.printStackTrace();
    }
  }

}
