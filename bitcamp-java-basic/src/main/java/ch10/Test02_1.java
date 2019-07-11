// 스태틱 멤버와 인스턴스 멤버
package ch10;

public class Test02_1{
  //[스태틱 멤버]
  //스태틱 필드
  //=>클래스가 로딩될 때 생성된다.
  static int f1;
  
  static {
    //스태틱 초기화 블록
    //=> 스태틱 필드가 모두 생성된 후 실행된다.
  }
  
  static void m1() {
    //스태틱 메서드
    //=>클래스 이름으로 호출한다.
  }
  //[인스턴스 멤버]
  //인스턴스 필드
  //=>new 명령을 실행할 때 Heap에 생성된다.
  int f2;
  
  {
    //인스턴스 초기화 블록
    //=>인스턴스 필드가 생성된 후 실행된다.
  }
  
  Test02_1() {
    //생성자 (메서드)
    //=>인스턴스 초기화 블록이 실행된 후 생성자가 호출된다.
    //  물론, new 명령에서 어떤 생성자를 호출할지 지정해야 한다.
  }
  void m2() {
    //인스턴스 메서드
    //=> 인스턴스가 있어야만 호출할 수 있다.
    //=>메서드를 호출할 때 넘겨준 인스턴스 주소는 
    //this 라는 내장 변수(built-in)에 인스턴스 주소가 보관된다.
  }
  
}




















