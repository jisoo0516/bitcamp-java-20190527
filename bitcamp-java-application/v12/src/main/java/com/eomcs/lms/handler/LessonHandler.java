package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.util.Input;

public class LessonHandler {
  private static Lesson[] lessons = new Lesson[100];
  private static int lessonssize = 0;
  public static Scanner keyScan;

  public static void addLesson() {
    
    Lesson lesson = new Lesson();
    
    lesson.no = Input.getIntValue("번호?");
    lesson.lectureName = Input.getStringValue("수업명? ");
    lesson.description = Input.getStringValue("설명? ");
    lesson.startDate = Input.getDateValue("시작일?");
    lesson.endDate = Input.getDateValue("종료일?");
    lesson.totalHours = Input.getIntValue("총수업시간?");
    lesson.dayHours = Input.getIntValue("일수업시간?");
    
    lessons[lessonssize++] = lesson;
    System.out.println("저장하였습니다.");
    
  }
 
  public static void listLesson() {
   
   for (int i = 0; i < lessonssize; i++) {
     Lesson lesson = new Lesson();
     lesson = lessons[i];
     System.out.printf("%s, %s, %s ~ %s, %s\n", lesson.no, lesson.lectureName, lesson.startDate,
         lesson.endDate, lesson.totalHours);
     
   }
 }

 
 
}
