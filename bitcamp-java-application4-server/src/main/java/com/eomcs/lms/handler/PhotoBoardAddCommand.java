package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.util.Input;

public class PhotoBoardAddCommand implements Command {

  private PhotoBoardDao photoBoardDao;


  public PhotoBoardAddCommand(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;


  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setTitle(Input.getStringValue(in, out, "내용?"));
      photoBoard.setLessonNo(Input.getIntValue(in, out, "번호?"));


      photoBoardDao.insert(photoBoard);
      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("데이터 저장에 실패");
      System.out.println(e.getMessage());
    }

  }
}
