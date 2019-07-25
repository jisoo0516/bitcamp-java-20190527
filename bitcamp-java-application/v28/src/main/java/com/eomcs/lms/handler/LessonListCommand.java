package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonListCommand implements Command {

  private List<Lesson> list;
  private Input input;

  public LessonListCommand(Input input, List<Lesson> list) {
    this.input = input;
    this.list = list;
  }

  @Override
  public void execute() {
    Lesson[] lessons = list.toArray(new Lesson[] {});
    for (Lesson lesson : lessons) {
      // 그 인스턴스 주소로 찾아가서 인스턴스의 각 변수 값을 꺼내 출력한다.
      System.out.printf("%s, %s, %s ~ %s, %s\n", lesson.getNo(), lesson.getTitle(),
          lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours());

    }
  }
}


