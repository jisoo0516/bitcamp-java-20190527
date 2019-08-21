package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.PhotoLesson;

// 수업 관리 DAO의 사용 규칙을 정의한다.
public interface PhotoLessonDao {

  int insert(PhotoLesson photolesson) throws Exception;

  List<PhotoLesson> findAll() throws Exception;

  PhotoLesson findBy(int no) throws Exception;

  int update(PhotoLesson photolesson) throws Exception;

  int delete(int no) throws Exception;



}
