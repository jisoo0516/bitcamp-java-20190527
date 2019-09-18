package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
@WebServlet("/photoboard/delete")
public class PhotoBoardDeleteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
 
  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;


  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx = (ApplicationContext) getServletContext().getAttribute("iocContainer");
    photoBoardDao = appCtx.getBean(PhotoBoardDao.class);
    photoFileDao = appCtx.getBean(PhotoFileDao.class);
  }
  


  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진 삭제</title>"
        + "<meta http-equiv='Refresh' content='1;url=/photoboard/list'>"
        + "</head>");
    out.println("<body><h1>사진삭제</h1>");
    try {

      int no = Integer.parseInt(request.getParameter("no"));


      if(photoBoardDao.findBy(no) == null) {
        out.println("<p>해당 데이터가 없습니다</p>");

      }else {
      // 먼저 게시물의 첨부파일을 삭제한다.
      System.out.println(photoBoardDao.findBy(no));
      photoFileDao.deleteAll(no);

      // 게시물을 삭제한다.
      photoBoardDao.delete(no);

      out.println("데이터 삭제하였습니다.");

      }
      
    } catch (Exception e) {
      out.println("<p>데이터 삭제 실패</p>");
      throw new RuntimeException(e);
    }finally {
      out.println("</body></html>");
    }
  }
}