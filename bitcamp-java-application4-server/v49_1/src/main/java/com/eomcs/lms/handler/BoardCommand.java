package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Component;
import com.eomcs.util.Input;
import com.eomcs.util.RequestMapping;

@Component
public class BoardCommand  {

  private BoardDao boardDao;


  public BoardCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
 @RequestMapping("/board/add") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void add(BufferedReader in, PrintStream out) {

    try {
      Board board = new Board();
      board.setContents(Input.getStringValue(in, out, "내용?"));

      boardDao.insert(board);
      out.println("저장하였습니다.");

    } catch (Exception e) {
      out.println("데이터 저장에 실패");
      System.out.println(e.getMessage());
    }

  }
 
 @RequestMapping("/board/delete") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
 public void delete(BufferedReader in, PrintStream out) {


   try {
     int no = Input.getIntValue(in, out,"번호?");
     
     if (boardDao.delete(no) > 0) {
       out.println("데이터 삭제");
     } else {
       out.println("해당 데이터 없음");
     }
     
   } catch (Exception e) {
     out.println("데이터 삭제 실패");
     System.out.println(e.getMessage());
   }
 }
 
 @RequestMapping("/board/detail") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
 public void detail(BufferedReader in, PrintStream out) {


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
 
 @RequestMapping("/board/list") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
 public void list(BufferedReader in, PrintStream out) {
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
 
 @RequestMapping("/board/update") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
 public void update(BufferedReader in, PrintStream out) {
   try {
     int no = Input.getIntValue(in, out, "번호?");

     Board board = boardDao.findBy(no);
     if (board == null) {
       out.println("해당 수업을 찾을 수 없습니다.");
       return;
     }
     
     Board data = new Board();
     data.setNo(no);

     String str = Input.getStringValue(in, out, "내용?");
     if (str.length() > 0) {
       data.setContents(str);

     } 

     boardDao.update(data);
     out.println("데이터를 수정하였습니다.");

   } catch (Exception e) {
     out.println("데이터 수정 실패");
     System.out.println(e.getMessage());
   }
 }


}
