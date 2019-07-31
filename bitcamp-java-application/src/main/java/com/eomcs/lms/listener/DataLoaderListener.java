package com.eomcs.lms.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;

// 애플리케이션이 시작되거나 종료될 때 보고를 받는 옵저버이다.
// 옵저버의 역할을 수행하려면 옵저버 호출 규칙에 따라 작성해야 한다.
// 즉 ApplicationContextListener를 구현해야 한다.
public class DataLoaderListener implements ApplicationContextListener{
  
  // Command 객체가 사용할 Collection 준비
  ArrayList<Lesson> lessonList = new ArrayList<>();
  ArrayList<Board> boardList = new ArrayList<>();
  ArrayList<Member> memberList = new ArrayList<>();
  
  // 애플리케이션이 시작될 때 수업관리 데이터를 로딩한다.
  @Override
  public void contextInitialized(Map<String,Object> beanContainer) {
    // 이전에 저장된 애플리케이션 데이터를 로딩한다.
    loadLessonData();
    loadBoardData();
    loadMemberData();
    
    
    // 옵저버에서 준비한 객체를 App 클래스에서 사용할 수 있도록
    // 빈 컨테이너에 저장한다.
    beanContainer.put("lessonList", lessonList);
    beanContainer.put("memberList", memberList);
    beanContainer.put("boardList", boardList);
  }
  
  // 애플리케이션이 종료될 떄 수업관리 데이터를 저장한다.
  @Override
  public void contextDestroyed(Map<String,Object> beanContainer) {
    // 애플리케이션의 실행을 종료하기 전에 데이터를 저장한다.
    saveLessonData();
    saveMemberData();
    saveBoardData();
    
  
  }
  private void loadLessonData() {
    // File의 정보를 준비
    File file = new File("./lesson.ser");

    // 바이트 단위로 출력된 데이터를 읽을 객체를 준비한다.
    FileInputStream in = null;
    ObjectInputStream in2 = null;
    try {
      in = new FileInputStream(file);

      // 바이트 배열을 읽어 객체로 복원해주는 객체 준비
      in2 = new ObjectInputStream(in);

      lessonList = (ArrayList<Lesson>) in2.readObject();

    } catch (FileNotFoundException e) {

      System.out.println("읽을 파일을 찾을 수 없습니다!");

    } catch (Exception e) {
      System.out.println("파일을 읽는 중에 오류가 발생했습니다.");
    } finally {
      try {
        in2.close();
      } catch (Exception e) {
      }
      try {
        in.close();
      } catch (Exception e) {
      }
    }
  }


  private void saveLessonData() {

    // File의 정보를 준비
    File file = new File("./lesson.ser");


    FileOutputStream out = null;
    ObjectOutputStream out2 = null;

    try {
      out = new FileOutputStream(file);

      // 객체를 통째로 바이트 배열로 변환해주는 출력 스트림 준비하기
      out2 = new ObjectOutputStream(out);



      // lessonList를 통째로 출력하기
      out2.writeObject(lessonList);

    } catch (FileNotFoundException e) {
      // 출력할 파일을 생성하지 못할 때
      // JVM을 멈추지 말고 간단히 오류 안내 문구를 출력한 다음에
      // 계속 실행하게 하자!
      System.out.println("파일을 생성할 수 없습니다!");

    } catch (IOException e) {
      System.out.println("파일에 데이터를 출력하는 중에 오류 발생!");
      e.printStackTrace(); // 오류가 발생한 이유를 상세하게 출력하기 위한 아이

    } finally {
      try {
        out2.close();
      } catch (Exception e) {
      }
      try {
        out2.close();
      } catch (Exception e) {
      }
    }
  }

  private void loadMemberData() {
    // File의 정보를 준비
    File file = new File("./member.ser");

    FileInputStream in = null;
    ObjectInputStream in2 = null;
    // 파일 정보를 바탕으로 데이터를 읽어주는 객체 준비
    try {
      in = new FileInputStream(file);
      in2 = new ObjectInputStream(in);
      memberList = (ArrayList<Member>) in2.readObject();


    } catch (FileNotFoundException e) {

      System.out.println("읽을 파일을 찾을 수 없습니다!");

    } catch (Exception e) {
      System.out.println("파일을 읽는 중에 오류가 발생했습니다.");
    } finally {
      try {
        in2.close();
      } catch (Exception e) {
      }
      try {
        in.close();
      } catch (Exception e) {
      }
    }
  }

  private void saveMemberData() {

    // File의 정보를 준비
    File file = new File("./member.ser");

    FileOutputStream out = null;
    ObjectOutputStream out2 = null;

    try {
      // 파일 정보를 바탕으로 데이터를 출력해주는 객체 준비
      out = new FileOutputStream(file);

      out2 = new ObjectOutputStream(out);

      out2.writeObject(memberList);

    } catch (FileNotFoundException e) {
      // 출력할 파일을 생성하지 못할 때
      // JVM을 멈추지 말고 간단히 오류 안내 문구를 출력한 다음에
      // 계속 실행하게 하자!
      System.out.println("파일을 생성할 수 없습니다!");

    } catch (IOException e) {
      System.out.println("파일에 데이터를 출력하는 중에 오류 발생!");
      e.printStackTrace();
    } finally {
      try {
        out2.close();
      } catch (Exception e) {
      }
      try {
        out2.close();
      } catch (Exception e) {
      }
    }
  }

  private void loadBoardData() {
    // File의 정보를 준비
    File file = new File("./board.ser");

    FileInputStream in = null;
    ObjectInputStream in2 = null;
    // 파일 정보를 바탕으로 데이터를 읽어주는 객체 준비
    try {
      in = new FileInputStream(file);

      // 바이트 배열을 읽어 원래의 타입인 int나 String 등으로 변환해주는 도구를
      // FileInputStream에 붙인다.
      in2 = new ObjectInputStream(in);

      boardList = (ArrayList<Board>) in2.readObject();


    } catch (FileNotFoundException e) {

      System.out.println("읽을 파일을 찾을 수 없습니다!");

    } catch (Exception e) {
      System.out.println("파일을 읽는 중에 오류가 발생했습니다.");
    } finally {
      try {
        in2.close();
      } catch (Exception e) {
      }
      try {
        in.close();
      } catch (Exception e) {
      }
    }
  }


  private void saveBoardData() {

    // File의 정보를 준비
    File file = new File("./board.ser");

    FileOutputStream out = null;
    ObjectOutputStream out2 = null;

    try {
      // 파일 정보를 바탕으로 데이터를 출력해주는 객체 준비
      out = new FileOutputStream(file);
      out2 = new ObjectOutputStream(out);

      out2.writeObject(boardList);
    } catch (FileNotFoundException e) {
      // 출력할 파일을 생성하지 못할 때
      // JVM을 멈추지 말고 간단히 오류 안내 문구를 출력한 다음에
      // 계속 실행하게 하자!
      System.out.println("파일을 생성할 수 없습니다!");

    } catch (IOException e) {
      System.out.println("파일에 데이터를 출력하는 중에 오류 발생!");
      e.printStackTrace();
    } finally {
      try {
        out2.close();
      } catch (Exception e) {
      }
      try {
        out2.close();
      } catch (Exception e) {
      }
    }
  }
}
