package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class MemberHandler {
  static Member[] members = new Member[100];
  static int memberssize = 0;
  static Scanner keyScan;
  
  static void addMember() {

    Member member = new Member();

    member.no = Input.getIntValue("번호?");
    member.name = Input.getStringValue("이름?");
    member.email = Input.getStringValue("메일?");
    member.password = Input.getIntValue("암호?");
    member.picture = Input.getStringValue("사진?");
    member.phoneNum = Input.getIntValue("폰번호?");
    member.joinDate = Input.getDateValue("가입일?");

    members[memberssize++] = member;
    System.out.println("저장하였습니다.");
  }
  
  static void listMember() {
    for (int i = 0; i < memberssize; i++) {
      Member member = members[i];
      System.out.printf("%s, %s, %s , %s\n", member.name, member.email, member.password,
          member.joinDate);

    }
  }
  

}
