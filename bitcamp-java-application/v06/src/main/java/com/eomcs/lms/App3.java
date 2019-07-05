package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;


public class App3 {
  static Scanner scan;

  public static void main(String[] args) {
    scan = new Scanner(System.in);

    int[] no = new int[100];
    String[] contents = new String[100];
    Date[] reportingDate = new Date[100];
    int[] hits = new int[100];

    int i = 0;
    for (; i < no.length; i++) {
      no[i] = getIntValue("번호?");
      contents[i]  = getStringValue("내용?");
      reportingDate[i]  = getDateValue("작성일?");
      hits[i]  = getIntValue("조회수?");

     System.out.println("계속 입력하시겠습니까?(Y/n)");

   
    
    String response = scan.nextLine();
    if(response.equals("n")) 
      break;
    }
    System.out.println();
    
    for(int i2 = 0; i2<=i; i2++) {
      System.out.printf("%s, %s, %s, %s\n" , no[i2], contents[i2],reportingDate[i2],hits[i2]  );
    }
    
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
