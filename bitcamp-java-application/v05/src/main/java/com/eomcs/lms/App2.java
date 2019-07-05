package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  static Scanner scan;

  public static void main(String[] args) {
    scan = new Scanner(System.in);

   
    int no =getIntValue("번호?");
    String name = getStringValue("이름?");
    String email = getStringValue("메일?");
    int password = getIntValue("암호?");
    String picture =  getStringValue("사진?");
    int phoneNum = getIntValue("폰번호?");
    java.sql.Date joinDate = getDateValue("가입일?");


    System.out.println();

    System.out.println("번호: " + no);
    System.out.println("이름: " + name);
    System.out.println("이메일: " + email);
    System.out.println("암호: " + password);
    System.out.println("사진: " + picture);
    System.out.println("전화: " + phoneNum);
    System.out.println("가입일: " + joinDate);

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
  
  private static java.sql.Date getDateValue(String message)  {
    while (true) {
      System.out.println(message);
      try {
        return Date.valueOf(scan.nextLine());
      } catch (Exception e) {
        System.out.println("2019-07-05 형식으로 입력해주세요.");
      }
    }
  }
  private static String getStringValue(String message)  {
    while (true) {
      System.out.println(message);
     
        return scan.nextLine();
    
    }
  }
  
}
