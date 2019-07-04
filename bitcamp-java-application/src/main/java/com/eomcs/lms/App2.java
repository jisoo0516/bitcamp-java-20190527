package com.eomcs.lms;
import java.util.Scanner;
public class App2 {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    
    System.out.print("번호?");
    String no = scan.nextLine();
    
    System.out.print("이름?");
    String name = scan.nextLine();
    
    System.out.print("메일?");
    String email = scan.nextLine();
    
    System.out.print("암호?");
    String password = scan.nextLine();
    
    System.out.print("사진?");
    String picture = scan.nextLine();
    
    System.out.print("폰번호?");
    String phoneNum = scan.nextLine();
    
    System.out.print("가입일?");
    String joinDate = scan.nextLine();
    
    
    System.out.println();
    
    System.out.println("번호: " + no);
    System.out.println("이름: " + name);
    System.out.println("이메일: " + email);
    System.out.println("암호: " + password);
    System.out.println("사진: " + picture);
    System.out.println("전화: " + phoneNum);
    System.out.println("가입일: " + joinDate);

  }
}
