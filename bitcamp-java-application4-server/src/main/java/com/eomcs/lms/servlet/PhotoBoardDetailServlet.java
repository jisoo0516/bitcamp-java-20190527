package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
@WebServlet("/photoboard/detail")
public class PhotoBoardDetailServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
 
  private PhotoBoardDao photoBoardDao;
  


  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx = (ApplicationContext) getServletContext().getAttribute("iocContainer");
    photoBoardDao = appCtx.getBean(PhotoBoardDao.class);
  }
  
 
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진 상세</title></head>");
    out.println("<body><h1>사진 상세</h1>");

    try {
      // 클라이언트에게 번호를 요구하여 받는다.
      int no =  Integer.parseInt(request.getParameter("no"));
      PhotoBoard photoBoard = photoBoardDao.findWithFilesBy(no);

      if (photoBoard == null) {
        out.println("<p>해당 번호의 데이터가 없습니다!</p>");

      }else {
        out.println("<form action='/photoboard/update' method='post'>");
        out.printf("번호 : <input type='text' name='no' value='%d' readonly><br>\n",
            photoBoard.getNo());
        out.printf("내용:<input type='text' name='title' value='%s'><br>\n", 
            photoBoard.getTitle());
        out.printf("작성일:<input type='text' name='reportingDate' value='%s'><br>\n", 
            photoBoard.getReportingDate());
        out.printf("조회수: <input type='text' name='hits' value='%d'><br>\n", 
            photoBoard.getHits());
        out.printf("번호: <input type='text' name='lessonNo' value='%d'><br>\n", 
            photoBoard.getLessonNo());
        out.println("<p>사진 파일:</p>");
        photoBoardDao.increaseViewCount(no);

        List<PhotoFile> files = photoBoard.getFiles();
        for (int i = 1; i <= 2; i++) {
          if (i <= files.size()) {
            out.printf("사진%d: <input type='text' name='filePath%d' value='%s'><br>\n",
                i, i, files.get(i-1).getFilePath());
          } else {
            out.printf("사진%d: <input type='text' name='filePath%d'><br>\n",
                i, i);
          }
        }
        out.println("<button>변경</button>");
        out.printf("<a href='/photoboard/delete?no=%d'>삭제</a>\n", 
            photoBoard.getNo());
        out.println("</form>");
      }
      
    } catch (Exception e) {
      out.println("<p>데이터 조회에 실패했습니다!</p>");
      throw new RuntimeException(e);
      
    } finally {
      out.println("</body></html>");
    }
  }
  

}