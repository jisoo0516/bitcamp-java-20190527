package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  static Scanner scan;

  public static void main(String[] args) {
    scan = new Scanner(System.in);

    Member [] members = new Member[100];

    
    int i = 0;
    
    for( ; i<members.length; i++) {
    Member member = new Member();
    
    member.no = getIntValue("번호?");
    member.name = getStringValue("이름?");
    member.email = getStringValue("메일?");
    member.password = getIntValue("암호?");
    member.picture = getStringValue("사진?");
    member.phoneNum = getIntValue("폰번호?");
    member.joinDate = getDateValue("가입일?");
    
    members[i] = member;
    
    System.out.println("계속 입력하시겠습니까?(Y/n)");
    String response = scan.nextLine();

    if(response.equals("n")) 
      break;
    
  }
    System.out.println();

    

    for (int i2 = 0;  i2 <=i;  i2++) {
      Member member = new Member();
      member = members[i2];
      System.out.printf("%s, %s, %s , %s\n",  member.name,  member.email,  member.password, member.joinDate);
   
    }
 }
 

  private static int getIntValue(String message) {

    while (true) {
      System.out.println(message);
      try {
        return Integer.parseInt(scan.nextLine());
      } catch (Exception e) {
        System.out.println("숫자를 입력해주세요.");
      }
    }
  }

  private static java.sql.Date getDateValue(String message) {
    while (true) {
      System.out.println(message);
      try {
        return Date.valueOf(scan.nextLine());
      } catch (Exception e) {
        System.out.println("2019-07-05 형식으로 입력해주세요.");
      }
    }
  }

  private static String getStringValue(String message) {
    while (true) {
      System.out.println(message);

      return scan.nextLine();

    }
  }

}
