package com.eomcs.util;

public class ListTest {

  public static void main(String[] args) {
    LinkedList<String> list1 = new LinkedList<>(); // ok
    //LinkedList<String> list2 = new ArrayList<>(); // 컴파일 오류!
    
    ArrayList<String> list3 = new ArrayList<>(); // ok
    //ArrayList<String> list4 = new LinkedList<>(); // 컴파일 오류!
    
    // 클래스 레퍼런스
    // => 해당 클래스의 인스턴스의 주소를 저장할 수 있다.
    // => 해당 클래스의 서브 클래스에 대해서도 인스턴스 주소를 저장할 수 있다.
    Object obj1 = new LinkedList<>(); // ok
    Object obj2 = new ArrayList<>();// ok
    Object obj3 = new String("Hello");// ok

    // 인터페이스 레퍼런스
    // => 해당 인터페이스에 따라 작성한 클래스의 인스턴스 주소를 저장할 수 있다.
    // => 해당 인터페이스를 구현한 클래스의 인스턴스 주소를 저장할 수 있다.
    // => 해당 인터페이스를 구현한 객체를 저장할 수 있다.
    // => 해당 인터페이스의 객체를 저장할 수 있다.
    // => List 객체를 저장할 수 있다.
    List<String> list5 = new LinkedList<>(); //ok
    List<String> list6 = new ArrayList<>(); //ok
    
  }

}
