package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

@WebServlet("/lesson/add")
public class LessonAddServlet extends HttpServlet  {
  private static final long serialVersionUID = 1L;
  
  private LessonDao lessonDao;

  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx = (ApplicationContext) getServletContext().getAttribute("iocContainer");
    lessonDao = appCtx.getBean(LessonDao.class);
  }
  
  @RequestMapping("/lesson/form")
  public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>수업 등록폼</title>"
        + "<link rel=\'stylesheet\' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\' integrity=\'sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\' crossorigin=\'anonymous\'>"
        + "<link rel=\'stylesheet\' href='/css/common.css'>"
        + "</head>");
    out.println("<body>");
    
    request.getRequestDispatcher("/header").include(request, response);
    
    out.println("<div id='content'>");
    
    out.println("<form action='/lesson/add'  method='post'>");
    out.println(" 수업명 : <textarea name='title' rows='5' cols='50'></textarea><br>");
    out.println(" 설명 : <textarea name='contents' rows='5' cols='50'></textarea><br>");
    out.println(" 시작일 : <textarea name='startDate' rows='5' cols='50'></textarea><br>");
    out.println(" 종료일 : <textarea name='endDate' rows='5' cols='50'></textarea><br>");
    out.println(" 총수업시간 : <textarea name='totalHours' rows='5' cols='50'></textarea><br>");
    out.println(" 일수업시간 : <textarea name='dayHours' rows='5' cols='50'></textarea><br>");
    out.println("<button>등록</button>");
    out.println(" </form>");
    out.println("</div>");
    request.getRequestDispatcher("/footer").include(request, response);
    out.println("</body></html>");
    
  }

  @RequestMapping("/lesson/add") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
    
    try {
      Lesson lesson = new Lesson();
      lesson.setTitle(request.getParameter("title"));
      lesson.setContents(request.getParameter("contents"));
      lesson.setStartDate(Date.valueOf(request.getParameter("startDate")));
      lesson.setEndDate(Date.valueOf(request.getParameter("endDate")));
      lesson.setTotalHours(Integer.parseInt(request.getParameter("totalHours")));
      lesson.setDayHours(Integer.parseInt(request.getParameter("dayHours")));
      lessonDao.insert(lesson);
      response.sendRedirect("/lesson/list");

    } catch (Exception e) {
      request.setAttribute("message", "데이터 입력에 실패");
      request.setAttribute("refresh", "/lesson/list");
      request.setAttribute("error", e);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
 
}


