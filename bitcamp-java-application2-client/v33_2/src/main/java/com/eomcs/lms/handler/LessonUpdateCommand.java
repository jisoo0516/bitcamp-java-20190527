package com.eomcs.lms.handler;

import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonUpdateCommand implements Command {

  private LessonDao lessonDao;
  private Input input;

  public LessonUpdateCommand(Input input, LessonDao lessonDao) {
    this.input = input;
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호?");

    try {
      Lesson lesson = lessonDao.findBy(no);
      if (lesson == null) {
        System.out.println("해당 수업을 찾을 수 없습니다.");
        return;
      }


      String str = input.getStringValue("수업명");
      if (str.length() > 0) {
        lesson.setTitle(str);

      }
      lessonDao.update(lesson);
      System.out.println("데이터 수정완료");



    } catch (Exception e) {
      System.out.println("데이터 삭제 실패");
      System.out.println(e.getMessage());

    }

  }

}


