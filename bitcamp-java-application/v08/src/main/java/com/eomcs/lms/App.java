// 애플리케이션 메인 클래스
// => 애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {

  static Scanner keyScan;

  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);
    

    Lesson[] lessons = new Lesson[100];
    int lessonssize = 0;
    
    Member [] members = new Member[100];
    int memberssize = 0;
    
    Board [] boards = new Board[100];
    int boardssize = 0;

    while (true) {

      System.out.print("명령>");
      String command = keyScan.nextLine();
      if (command.equals("quit")) {
        break;

      } else if (command.equals("/lesson/add")) {


      

          Lesson lesson = new Lesson();

          lesson.no = getIntValue("번호?");
          lesson.lectureName = getStringValue("수업명? ");
          lesson.description = getStringValue("설명? ");
          lesson.startDate = getDateValue("시작일?");
          lesson.endDate = getDateValue("종료일?");
          lesson.totalHours = getIntValue("총수업시간?");
          lesson.dayHours = getIntValue("일수업시간?");

          lessons[lessonssize++] = lesson;
          System.out.println("저장하였습니다.");

        


      } else if(command.equals("/member/add")) {
        Member member = new Member();
        
        member.no = getIntValue("번호?");
        member.name = getStringValue("이름?");
        member.email = getStringValue("메일?");
        member.password = getIntValue("암호?");
        member.picture = getStringValue("사진?");
        member.phoneNum = getIntValue("폰번호?");
        member.joinDate = getDateValue("가입일?");
        
        members[memberssize++] = member;
        System.out.println("저장하였습니다.");
      
      } else if(command.equals("/board/add")) {
        Board board = new Board();
        board.no = getIntValue("번호?");
        board.contents  = getStringValue("내용?");
        board.reportingDate  = getDateValue("작성일?");
        board.hits  = getIntValue("조회수?");

        boards[boardssize++] = board;
        System.out.println("저장하였습니다.");
      
      }else if (command.equals("/lesson/list")) {



        for (int i = 0; i < lessonssize; i++) {
          Lesson lesson = new Lesson();
          lesson = lessons[i];
          System.out.printf("%s, %s, %s ~ %s, %s\n", lesson.no, lesson.lectureName,
              lesson.startDate, lesson.endDate, lesson.totalHours);

        }
      }else if(command.equals("/member/list")) {

        for (int i = 0;  i <memberssize;  i++) {
          Member member = new Member();
          member = members[i];
          System.out.printf("%s, %s, %s , %s\n",  member.name,  member.email,  member.password, member.joinDate);
       
        }
        
      }else if(command.equals("/board/list")) {
        
        for(int i = 0; i<boardssize; i++) {
          Board board = new Board();
          board = boards[i];
          
          System.out.printf("%s, %s, %s, %s\n" , board.no, board.contents,board.reportingDate,board.hits  );
        }
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }



  }

  private static int getIntValue(String message) {

    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
  }

  private static Date getDateValue(String message) {

    while (true) {
      try {
        System.out.print(message);
        return Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-07-05 형식으로 입력하세요.");
      }
    }
  }

  private static String getStringValue(String message) {


    System.out.print(message);
    return keyScan.nextLine();


  }
}
