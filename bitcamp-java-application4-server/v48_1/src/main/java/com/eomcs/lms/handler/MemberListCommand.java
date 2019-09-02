package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Component;
import com.eomcs.util.RequestMapping;
@Component("/member/list")
public class MemberListCommand  {

  private MemberDao memberDao;


  public MemberListCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
 
  @RequestMapping // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void execute(BufferedReader in, PrintStream out) {

    try {
      List<Member> members = memberDao.findAll();
      for (Member member : members) {
        out.printf("%s,%s,%s,%s,%s\n", member.getNo(), member.getName(), member.getEmail(), member.getPhoneNum(),
            member.getJoinDate());

      }

    } catch (Exception e) {
      out.println("데이터 조회 실패");
      System.out.println(e.getMessage());
    }
  }


}
