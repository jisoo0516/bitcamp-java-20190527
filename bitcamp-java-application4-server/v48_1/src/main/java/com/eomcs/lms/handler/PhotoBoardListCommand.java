package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.util.Component;
import com.eomcs.util.RequestMapping;
@Component("/photoboard/list")
public class PhotoBoardListCommand {

  private PhotoBoardDao photoBoardDao;


  public PhotoBoardListCommand(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;

  }
 


  @RequestMapping // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void execute(BufferedReader in, PrintStream out) {
    try {
      List<PhotoBoard> photoBoards = photoBoardDao.findAll();
      for (PhotoBoard photoBoard : photoBoards) {
        out.printf("%d, %s, %s, %d, %d\n", photoBoard.getNo(), photoBoard.getTitle(),
            photoBoard.getReportingDate(), photoBoard.getHits(),
        photoBoard.getLessonNo());
      }
    } catch (Exception e) {
      out.println("데이터 목록조회 실패");   
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
  }

}
