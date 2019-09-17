package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.MemberDao;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
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
    out.println("<html><head><title>로그인</title></head>");
    out.println("<body><h1>로그인</h1>");

    try {
      HashMap<String,Object> parms = new HashMap<>();
      parms.put("email",request.getParameter("email"));
      parms.put("password",request.getParameter("password"));

      com.eomcs.lms.domain.Member member = memberDao.findByEmailPassword(parms);
      if(member == null) {
        out.println("<p>이메일 또는 암호가 맞지 않습니다!</p>");
      } else {
        out.printf("<p>%s님 환영합니다. \n</p>", member.getName());
      }

    } catch (Exception e) {
      out.println("<p>로그인 실행에 실패했습니다.</p>");
      throw new RuntimeException(e);   
    }

  }
}
