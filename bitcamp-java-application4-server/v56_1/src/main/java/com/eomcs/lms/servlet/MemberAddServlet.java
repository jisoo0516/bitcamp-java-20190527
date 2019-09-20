package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  private MemberDao memberDao;
  
  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx = (ApplicationContext) getServletContext().getAttribute("iocContainer");
    memberDao = appCtx.getBean(MemberDao.class);
  }
  
 
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>멤버 등록폼</title></head>");
    out.println("<body><h1>멤버 등록폼</h1>");
    out.println("<form action='/member/add' method='post'>");
    out.println(" 이름 : <input type='text' name = 'name'><br>");
    out.println(" 메일 : <input type='text' name = 'email'><br>");
    out.println(" 비밀번호 : <input type='text' name = 'password'><br>");
    out.println(" 사진 : <input type='text' name = 'picture'><br>");
    out.println(" 전화 :<input type='text' name = 'phoneNum'><br>");
    out.println("<button>등록</button>");
    out.println(" </form>");
    out.println("</body></html>");
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>멤버 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=/member/list'>"
        + "</head>");
    out.println("<body><h1>멤버 등록</h1>");

    try {
      Member member = new Member();
      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPassword(request.getParameter("password"));
      member.setPicture(request.getParameter("picture"));
      member.setPhoneNum(request.getParameter("phoneNum"));
      System.out.println(member);
      
      memberDao.insert(member);

      out.println("<p>저장하였습니다.</p>");

    } catch (Exception e) {
      out.println("<p>데이터 저장에 실패</p>");
      throw new RuntimeException(e);
    }
    out.println("<body><html>");
  }
 
}