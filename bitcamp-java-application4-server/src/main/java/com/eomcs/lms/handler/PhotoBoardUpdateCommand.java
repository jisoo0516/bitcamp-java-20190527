package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.util.Input;

public class PhotoBoardUpdateCommand implements Command {

  private PhotoBoardDao photoBoardDao;


  public PhotoBoardUpdateCommand(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }


  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호?");

      PhotoBoard photoBoard = photoBoardDao.findBy(no);
      if (photoBoard == null) {
        out.println("해당 수업을 찾을 수 없습니다.");
        return;
      }

      String str = Input.getStringValue(in, out, "내용?");
      if (str.length() > 0) {
        photoBoard.setTitle(str);
        photoBoardDao.update(photoBoard);
        out.println("데이터를 수정하였습니다.");

      } else {
        out.println("데이터 변경을 취소합니다.");
      }


    } catch (Exception e) {
      out.println("데이터 수정 실패");
      System.out.println(e.getMessage());
    }
  }

}
