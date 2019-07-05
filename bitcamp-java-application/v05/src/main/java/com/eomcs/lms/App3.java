package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;


public class App3 {
  static Scanner scan;

  public static void main(String[] args) {
    scan = new Scanner(System.in);

    int no = getIntValue("번호?");
    String contents = getStringValue("내용?");
    java.sql.Date reportingDate = getDateValue("작성일?");
    int hits = getIntValue("조회수?");

    System.out.println();
    System.out.println("번호: " + no);
    System.out.println("내용: " + contents);
    System.out.println("작성일: " + reportingDate);
    System.out.println("조회수: " + hits);
  }

  private static int getIntValue(String message) {
    while (true) {
      try {
        System.out.println(message);
        return Integer.parseInt(scan.nextLine());
      } catch (Exception e) {
        System.out.println("숫자를 입력해주세요.");
      }
    }
  }

  private static String getStringValue(String message) {

    System.out.println(message);
    return scan.nextLine();


  }

  private static java.sql.Date getDateValue(String message) {
    while (true) {
      try {
        System.out.println(message);
        return Date.valueOf(scan.nextLine());
      } catch (Exception e) {
        System.out.println("2017-07-05 형식으로 입력해주세요..");
      }
    }
  }
}
