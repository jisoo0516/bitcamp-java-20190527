package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonListCommand implements Command {

  private LessonDao lessonDao;
  private Input input;

  public LessonListCommand(Input input, LessonDao lessonDao) {
    this.input = input;
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      List<Lesson> lessons = lessonDao.findAll();
      for (Lesson lesson : lessons) {
        // 그 인스턴스 주소로 찾아가서 인스턴스의 각 변수 값을 꺼내 출력한다.
        out.printf("%s, %s, %s ~ %s, %s\n", lesson.getNo(), lesson.getTitle(),
            lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours());

      }
    } catch (Exception e) {
      out.println("데이터 조회 실패");
      System.out.println(e.getMessage());
    }
  }
}


