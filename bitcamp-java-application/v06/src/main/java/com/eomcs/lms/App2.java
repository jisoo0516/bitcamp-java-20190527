package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  static Scanner scan;

  public static void main(String[] args) {
    scan = new Scanner(System.in);



    int[] no = new int[100];
    String[] name = new String[100];
    String[] email = new String[100];
    int[] password = new int[100];
    String[] picture = new String[100];
    int[] phoneNum = new int[100];
    Date[] joinDate = new Date[100];
    int i = 0;
    
    for( ; i<no.length; i++) {
    no[i] = getIntValue("번호?");
    name[i] = getStringValue("이름?");
    email[i] = getStringValue("메일?");
    password[i] = getIntValue("암호?");
    picture[i] = getStringValue("사진?");
    phoneNum[i] = getIntValue("폰번호?");
    joinDate[i] = getDateValue("가입일?");
    
    System.out.println("계속 입력하시겠습니까?(Y/n)");
    String response = scan.nextLine();

    if(response.equals("n")) 
      break;
    
  }
    System.out.println();

    

    for (int i2 = 0;  i2 <=i;  i2++) {
      System.out.printf("%s, %s, %s , %s\n",  name[i2], email[i2], password[i2],joinDate[i2]);
   
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
