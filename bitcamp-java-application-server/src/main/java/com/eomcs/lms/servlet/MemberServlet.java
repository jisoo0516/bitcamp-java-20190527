package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.dao.MemberSerialDao;
import com.eomcs.lms.domain.Member;

public class MemberServlet implements Servlet {

  MemberSerialDao memberDao;

  ObjectInputStream in;
  ObjectOutputStream out;
  
  
 
  public MemberServlet(ObjectInputStream in, ObjectOutputStream out) throws Exception{
    this.in = in;
    this.out = out;
    
    memberDao = new MemberSerialDao("./memberser");
    
   
  }
  public void saveData() {
    memberDao.saveData();
  }
  
  
  

  
  @Override
  public void service(String command) throws Exception {
  switch (command) {
    case "/member/add":
      addMember();
      break;
    case "/member/list":
      listMember();
      break;
    case "/member/delete":
      deleteMember();
      break;
    case "/member/detail":
      detailMember();
      break;
    case "/member/update":
      updateMember();
      break;
    default:
      out.writeUTF("fail");
      out.writeUTF("지원하지 않는 명령입니다.");
  }
}
  
  
  private void updateMember() throws Exception {
    Member member = (Member) in.readObject();


    if (memberDao.update(member) == 0) {
      fail("해당 번호의 게시물이 없습니다");
      return;
    }
    out.writeUTF("ok");

  }

  private  void detailMember() throws Exception {
    int no = in.readInt();

    Member member = memberDao.findBy(no);
    if (member == null) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(member);
  }


  private  void deleteMember() throws Exception {
    int no = in.readInt();


    if (memberDao.delete(no) == 0) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }


    out.writeUTF("ok");
  }



  private  void addMember() throws Exception {
    Member member = (Member) in.readObject();
    if(memberDao.insert(member) ==0) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
     out.writeUTF("ok");
   }

  private  void listMember() throws Exception {
    out.writeUTF("ok");
    out.reset(); // 기존에 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(memberDao.findAll());
  }


  
  
  private  void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }

}
