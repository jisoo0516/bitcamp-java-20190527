package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class BoardHandler {
  static Board[] boards = new Board[100];
  static int boardssize = 0;
  static Scanner keyScan;
  
  static void addBoard() {
    Board board = new Board();

    board.no = Input.getIntValue("번호?");
    board.contents = Input.getStringValue("내용?");
    // board.reportingDate = getDateValue("작성일?");
    board.reportingDate = new Date(System.currentTimeMillis());
    board.hits = Input.getIntValue("조회수?");

    boards[boardssize++] = board;
    System.out.println("저장하였습니다.");

  }
  
  static void listBoard() {
    for (int i = 0; i < boardssize; i++) {

      Board board = boards[i];
      System.out.printf("%s, %s, %s, %s\n", board.no, board.contents, board.reportingDate,
          board.hits);
    }
  }
 
}
