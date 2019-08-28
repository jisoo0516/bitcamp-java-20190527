package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonUpdateCommand implements Command {

  private LessonDao lessonDao;

  public LessonUpdateCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호?");
      Lesson lesson = lessonDao.findBy(no);
      if (lesson == null) {
        System.out.println("해당 수업을 찾을 수 없습니다.");
        return;
      }


      String str = Input.getStringValue(in, out, "수업명");
      if (str.length() > 0) {
        lesson.setTitle(str);
        
        lesson.setStartDate(
            Input.getDateValue(in, out, "시작일(" + lesson.getStartDate() + ")? "));

        lesson.setEndDate(
            Input.getDateValue(in, out, "종료일(" + lesson.getEndDate() + ")? "));

        lesson.setTotalHours(
            Input.getIntValue(in, out, "총수업시간(" + lesson.getTotalHours() + ")? "));

        lesson.setDayHours(
            Input.getIntValue(in, out, "일수업시간(" + lesson.getDayHours() + ")? "));

        lessonDao.update(lesson);
        out.println("데이터 수정완료");
    

      } else {
        out.println("데이터 변경을 취소합니다.");
      }



    } catch (Exception e) {
      System.out.println("데이터 수정 실패");
      System.out.println(e.getMessage());

    }

  }

}


