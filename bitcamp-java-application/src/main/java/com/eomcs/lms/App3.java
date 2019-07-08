package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;


public class App3 {
  static Scanner scan;

  public static void main(String[] args) {
    scan = new Scanner(System.in);
    
    Board[] boards = new Board[100];

  
    int i = 0;
    for (; i < boards.length; i++) {
      
      Board board = new Board();
      
      board.no = getIntValue("번호?");
      board.contents  = getStringValue("내용?");
      board.reportingDate  = getDateValue("작성일?");
      board.hits  = getIntValue("조회수?");
      
      boards[i] = board;


     System.out.println("계속 입력하시겠습니까?(Y/n)");

   
    
    String response = scan.nextLine();
    if(response.equals("n")) 
      break;
    }
    System.out.println();
    
    for(int i2 = 0; i2<=i; i2++) {
      
      Board board = boards[i2];
      System.out.printf("%s, %s, %s, %s\n" , board.no, board.contents,board.reportingDate,board.hits );
    }
    
  }
  

  private static int getIntValue(String message) {
    while (true) {
      try {
        System.out.println(message);
        return Integer.parseInt(scan.nextLine());
      } catch (Exception e) {
        System.out.println("숫자를 입력해주세요.");
      }
    }
  }

  private static String getStringValue(String message) {

    System.out.println(message);
    return scan.nextLine();


  }

  private static java.sql.Date getDateValue(String message) {
    while (true) {
      try {
        System.out.println(message);
        return Date.valueOf(scan.nextLine());
      } catch (Exception e) {
        System.out.println("2017-07-05 형식으로 입력해주세요..");
      }
    }
  }
}
