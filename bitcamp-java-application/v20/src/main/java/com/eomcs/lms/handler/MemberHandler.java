package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;
import com.eomcs.util.LinkedList;

public class MemberHandler {

  private LinkedList<Member> memberList = new LinkedList<>();

  private Input input;

  public MemberHandler(Input input) {
    this.input = input;
  }

  public void addMember() {

    Member member = new Member();

    member.setNo(input.getIntValue("번호?"));
    member.setName(input.getStringValue("이름?"));
    member.setEmail(input.getStringValue("메일?"));
    member.setPassword(input.getIntValue("암호?"));
    member.setPicture(input.getStringValue("사진?"));
    member.setPhoneNum(input.getIntValue("폰번호?"));
    member.setJoinDate(input.getDateValue("가입일?"));

    memberList.add(member);
    System.out.println("저장하였습니다.");
  }

  public void listMember() {
    Member[] members = memberList.toArray(new Member[] {});
    for (Member member : members) {
      System.out.printf("%s, %s, %s , %s\n", member.getName(), member.getEmail(),
          member.getPassword(), member.getJoinDate());

    }
  }

  public void detailMember() {

    int no = input.getIntValue("번호?");
    Member member = null;
    for (int i = 0; i < memberList.size(); i++) {
      Member temp = memberList.get(i);
      if (temp.getNo() == no) {
        member = temp;
        break;

      }
      if (member == null) {
        System.out.println("해당 학생을 찾을 수 없습니다.");
        return;
      }
    }



  }

  public void updateMember() {
    int no = input.getIntValue("번호?");

    Member member = null;
    for (int i = 0; i < memberList.size(); i++) {
      Member temp = memberList.get(i);
      if (temp.getNo() == no) {
        member = temp;
        break;
      }
    }
    if (member == null) {
      System.out.println("해당 학생을 찾을 수 없습니다.");
      return;
    }
    String str = input.getStringValue("이름:(" + member.getName() + ")?");
    if (str.length() > 0) {
      member.setName(str);
    }
    str = input.getStringValue("메일?");
    if (str.length() > 0) {
      member.setEmail(str);
    }

    member.setPassword(input.getIntValue("패스워드(" + member.getPassword() + ")? "));

    str = input.getStringValue("사진?");
    if (str.length() > 0) {
      member.setPicture(str);
    }
    member.setPhoneNum(input.getIntValue("폰번호(" + member.getPhoneNum() + ")? "));

    member.setJoinDate(input.getDateValue("시작일(" + member.getJoinDate() + ")? "));

  }



  

  public void deleteMember() {
    int no = input.getIntValue("번호?");

    // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Lesson 객체를 찾는다.
    for (int i = 0; i < memberList.size(); i++) {
      Member temp = memberList.get(i);
      if (temp.getNo() == no) {
        memberList.remove(i);
        System.out.println("데이터를 삭제하였습니다");
        return;
      }
    }

    System.out.println("해당 수업을 찾을 수 없습니다.");

  }


}
