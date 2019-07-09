package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.util.Input;

public class BoardHandler {
   private static Board[] boards = new Board[100];
   private static int boardssize = 0;
  
   public static Scanner keyScan;
  
   public static void addBoard() {
    Board board = new Board();
    board.no = Input.getIntValue("번호?");
    board.contents = Input.getStringValue("내용?");
    board.reportingDate = Input.getDateValue("작성일?");
    board.hits = Input.getIntValue("조회수?");
    
    boards[boardssize++] = board;
    System.out.println("저장하였습니다.");
  }

   public static void listBoard() {
    for (int i = 0; i < boardssize; i++) {
      Board board = new Board();
      board = boards[i];
      
      System.out.printf("%s, %s, %s, %s\n", board.no, board.contents, board.reportingDate,
          board.hits);
    }
  }
  
}
