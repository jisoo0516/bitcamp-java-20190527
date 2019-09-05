package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;
@Component
public class MemberCommand {


  private MemberDao memberDao;



  public MemberCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @RequestMapping("/member/add") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void add(BufferedReader in, PrintStream out) {


    try {
      Member member = new Member();

      member.setName(Input.getStringValue(in, out, "이름?"));
      member.setEmail(Input.getStringValue(in, out, "메일?"));
      member.setPassword(Input.getStringValue(in, out, "암호?"));
      member.setPicture(Input.getStringValue(in, out, "사진?"));
      member.setPhoneNum(Input.getStringValue(in, out, "폰번호?"));
      memberDao.insert(member);

      out.println("저장하였습니다.");
    } catch (Exception e) {
      out.println("데이터 저장에 실패");
      System.out.println(e.getMessage());
    }
  }

  @RequestMapping("/member/delete") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void delete(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호?");

      if (memberDao.delete(no) > 0) {
        out.println("데이터 삭제");
      } else {
        out.println("해당 데이터 없음");
      }

    } catch (Exception e) {
      out.println("데이터 삭제 실패");
      System.out.println(e.getMessage());
    }

  }

  @RequestMapping("/member/detail") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void detail(BufferedReader in, PrintStream out) {



    try {
      int no = Input.getIntValue(in, out, "번호?");
      Member member = memberDao.findBy(no);

      if (member == null) {
        out.println("해당 학생을 찾을 수 없습니다.");
        return;
      }
      out.printf("이름: %s\n", member.getName());
      out.printf("이메일: %s\n", member.getEmail());
      out.printf("암호: %s\n", member.getPassword());
      out.printf("사진: %s\n", member.getPicture());
      out.printf("전화: %s\n", member.getPhoneNum());
      out.printf("가입일: %s\n", member.getJoinDate());

    } catch (Exception e) {
      out.println("데이터 조회에 실패");
      System.out.println(e.getMessage());

    }
  }

  @RequestMapping("/member/list") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void list(BufferedReader in, PrintStream out) {

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

  @RequestMapping("/member/update") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void update(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호?");

      Member member = memberDao.findBy(no);
      if (member == null) {
        System.out.println("해당 학생을 찾을 수 없습니다.");
        return;
      }
      // 사용자로부터 변경할 값을 입력 받는다.
      Member data = new Member();
      data.setNo(no);

      String str = Input.getStringValue(in, out, "이름");
      if (str.length() > 0) {
        data.setName(str);
      }

      str = Input.getStringValue(in, out, "메일?");
      if (str.length() > 0) {
        data.setEmail(str);
      }

      str = Input.getStringValue(in, out, "암호? ");
      if (str.length() > 0) {
        data.setPassword(str);
      }

      str = Input.getStringValue(in, out, "사진?");
      if (str.length() > 0) {
        data.setPicture(str);
      }

      str = Input.getStringValue(in, out, "폰번호?");
      if (str.length() > 0) {
        data.setPhoneNum(str);

      }
      memberDao.update(data);
      out.println("데이터를 수정하였습니다.");


    } catch (Exception e) {
      out.println("데이터 변경 실패");
      System.out.println(e.getMessage());
    }

  }
  
  @RequestMapping("/member/search") // 클라이언트 요청이 들어왔을 때 이 메서드를 호출하라고 표시한다.
  public void search(BufferedReader in, PrintStream out) {

    try {
      String keyword = Input.getStringValue(in, out, "검색어?");
      
      List<Member> members = memberDao.findByKeyword(keyword);
      for (Member member : members) {
        out.printf("%s,%s,%s,%s,%s\n", member.getName(), member.getEmail(), member.getPhoneNum(),
            member.getPicture(), member.getJoinDate());

      }

    } catch (Exception e) {
      out.println("데이터 조회 실패");
      System.out.println(e.getMessage());
    }
  }
}
