
package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class ServerTest {

  public static void main(String[] args) throws Exception {
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
    try (Socket socket = new Socket("192.168.0.49", 8888);
        ObjectOutputStream out =
            new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in =
            new ObjectInputStream(socket.getInputStream())) {
      

      
   
    
      System.out.println("서버와 연결되었음!");
     
      
      Member member = new Member();
      member.setNo(1);
      member.setName("홍길동");
      member.setEmail("hong@test.com");
      member.setPicture("hong.gif");
      member.setPhoneNum("1111");
      
      out.writeUTF("add");
      out.writeObject(member);
      out.flush();
      System.out.println("add 요청함.");
      
      // 서버가 보낸 데이터를 읽는다.
      String response = in.readUTF();
      System.out.println("==>" + response);
      
      member = new Member();
      member.setNo(2);
      member.setName("임꺽정");
      member.setEmail("leem@test.com");
      member.setPicture("leem.gif");
      member.setPhoneNum("2222");
      
      out.writeUTF("add");
      out.writeObject(member);
      out.flush();
      System.out.println("add 요청함");
      
      // 서버가 보낸 데이터를 읽는다.
      response = in.readUTF();
      System.out.println("==>" + response);
     
      out.writeUTF("list");
      out.flush();
      System.out.println("list 요청함");
      
      response = in.readUTF();
      System.out.println("==>"+response);
      
      @SuppressWarnings("unchecked")
      List<Member> list = (List<Member>)in.readObject();
          System.out.println("--------------------");
      for(Member m : list) {
        System.out.println(m);
      }
      System.out.println("-----------------------");
    
      // 서버가 처리할수 없는 명령어 보내기
      out.writeUTF("delete");
      out.flush();
      System.out.println("delete 요청함");
      
      // 서버가 보낸 데이터를 읽는다.
      response = in.readUTF();
      System.out.println("==>"+response);
      
      response = in.readUTF();
      System.out.println("==>"+response);
      
      // 서버가 처리할수 없는 명령어 보내기
      out.writeUTF("quit");
      out.flush();
      System.out.println("quit 요청함");
      
      // 서버가 보낸 데이터를 읽는다.
      response = in.readUTF();
      System.out.println("==>"+response);
      
      
      
    } catch (IOException e) {
      // 예외가 발생하면 일단 어디에서 예외가 발생했는지 확인하기 위해 호출 정보를 모두 출력한다.
      e.printStackTrace();
    }

    System.out.println("서버 종료!");
  }
}
