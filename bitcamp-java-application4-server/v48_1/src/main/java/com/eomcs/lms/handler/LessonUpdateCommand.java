package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Component;
import com.eomcs.util.Input;
import com.eomcs.util.RequestMapping;
@Component("/lesson/update")
public class LessonUpdateCommand  {

  private LessonDao lessonDao;

  public LessonUpdateCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  
  @RequestMapping // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void execute(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호?");
      Lesson lesson = lessonDao.findBy(no);
      if (lesson == null) {
        out.println("해당 수업을 찾을 수 없습니다.");
        return;
      }
      
      Lesson data = new Lesson();
      data.setNo(no);

      String str = Input.getStringValue(in, out, "수업명");
      if (str.length() > 0) {
        data.setTitle(str);
        
        str = Input.getStringValue(in, out, "수업내용? ");
        if (str.length() > 0) {
          data.setContents(str);
        }
        
        try {
        data.setStartDate(
            Input.getDateValue(in, out, "시작일(" + lesson.getStartDate() + ")? "));
        } catch (Exception e) {}

        try {
        data.setEndDate(
            Input.getDateValue(in, out, "종료일(" + lesson.getEndDate() + ")? "));
        } catch (Exception e) {}

        try {
        data.setTotalHours(
            Input.getIntValue(in, out, "총수업시간(" + lesson.getTotalHours() + ")? "));
        } catch (Exception e) {}

        try {
        data.setDayHours(
            Input.getIntValue(in, out, "일수업시간(" + lesson.getDayHours() + ")? "));
        } catch (Exception e) {}
      } 

      lessonDao.update(data);
      out.println("데이터 수정완료");

    } catch (Exception e) {
      System.out.println("데이터 수정 실패");
      System.out.println(e.getMessage());

    }

  }

}

