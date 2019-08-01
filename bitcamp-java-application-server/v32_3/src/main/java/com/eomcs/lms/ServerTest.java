
package com.eomcs.lms;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

  public static void main(String[] args) {
    System.out.println("[수업관리시스템 서버 애플리케이션 테스트]");

    // 서버에 통신을 연결한다.
    // => new Socket(인터넷주소, 포트번호)
    // => 서버와 연결될 때까지 리턴하지 않는다.
    //
    // 인터넷 주소?
    // => IP 주소(예: 192.168.0.1)
    // => 도메인 이름(예: www.koko.com)
    //
    // localhost?
    // => 로컬 PC를 가리키는 특수 도메인 이름이다.
    //
    // 127.0.0.1?
    // => 로컬 PC를 가리키는 특수 IP 주소이다.
    //
    //
    try (Socket socket = new Socket("192.168.0.51", 8888);
    PrintWriter out = new PrintWriter(
        new BufferedOutputStream(socket.getOutputStream()));
  
    BufferedReader in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()))) {
      // 서버와의 입출력을 위해 스트림 객체를 준비한다.
      

      
   
    
      System.out.println("서버와 연결되었음!");
     
      // 서버에 출력한다.
      out.println("'지수'입니다!");
      out.flush(); // 주의! 버퍼로 출력한 내용을 서버로 보내도록 강제해야한다.
      System.out.println("서버에 데이터를 보냈음.");
      
      // 서버가 보낸 데이터를 읽는다.
      String response = in.readLine();
      System.out.println("서버로부터 데이터를 받았음");
      
      // 서버가 보낸 데이터를 콘솔창에 출력한다.
      System.out.println("-->"+response);

      // => 클라이언트의 연결 요청이 들어올 때까지 기다리다가
      // 요청이 들어오는 즉시 승일을 한 후, 연결 정보를 리턴한다.
     
    
    } catch (IOException e) {
      // 예외가 발생하면 일단 어디에서 예외가 발생했는지 확인하기 위해 호출 정보를 모두 출력한다.
      e.printStackTrace();
    }

    System.out.println("서버 종료!");
  }
}
