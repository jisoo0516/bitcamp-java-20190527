package com.eomcs.lms.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.MemberDao;
@Component
public class LoginCommand {

  private MemberDao memberDao;

 
  public LoginCommand(MemberDao memberDao) {
    this.memberDao = memberDao;


  }
 
  @RequestMapping("/login/form")
  public void form(ServletRequest request, ServletResponse response)throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<form action='/member/list'>");
    out.println(" 메일 : <input type='text' name = 'name'>");
    out.println(" 비밀번호 : <input type='text' name = 'name'>");
    out.println("<button>로그인</button>");
    out.println(" </form>>");
   
  }
  
  @RequestMapping("/auth/login") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void execute(ServletRequest request, ServletResponse response)throws IOException {
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
