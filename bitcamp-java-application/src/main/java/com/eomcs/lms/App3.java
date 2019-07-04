package com.eomcs.lms;

import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    
    System.out.print("번호?");
    String no = scan.nextLine();
    
    System.out.print("내용?");
    String contents = scan.nextLine();
    
    System.out.print("작성일?");
    String reportingDate = scan.nextLine();
    
    System.out.print("조회수?");
    String hits= scan.nextLine();
    
    System.out.println();
    System.out.println("번호: " + no);
    System.out.println("내용: " + contents);
    System.out.println("작성일: " + reportingDate);
    System.out.println("조회수: " + hits);
  }

}
