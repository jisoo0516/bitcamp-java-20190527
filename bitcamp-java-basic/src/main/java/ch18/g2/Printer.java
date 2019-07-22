// 디폴트 메서드 - 기존 코드에 영향을 미치지 않고 새 규칙을 추가하는 방법
package ch18.g2;

public interface Printer {
  
  // 일반적으로 선언하는 인터페이스의 메서드는 public abstract이다.
  // 따라서 이 인터페이스를 구현하는 모든 클래스가 반드시 이 메서드를 정의해야 한다.
  void print(String text);
  
  // 다음과 같이 새 기능을 무심코 추가한다면
  // 기존의 이 인터페이스 규칙에 따라 작성한 모든 클래스들에
  // 컴파일 오류가 발생할 것이다.
  // 왜? 새 규칙(메서드)이 추가되었기 때문이다.
  // 새 규칙이 추가되었으면 기존 클래스들도 새 규칙을 구현해야 한다.
  //
  //void watermark(String title);
  
  
  // 디폴트 메서드를 사용하면 기존 구현체(인터페이스를 구현한 클래스)에 영향을 끼치지 않으면서
  // 새 규칙을 추가할 수 있다.
  // => 디폴트 메서드를 추가할 때 간단하게 코드를 작성할 수 있지만
  //    클래스처럼 뭔가 진짜 일을 하는 코드를 작성하는 것은 좋지 않다.
  //    그냥 다음과 같이 빈 메서드를 만들라!
  default void watermark(String title) {};
  // 이 메서드를 추가했다고 해서
  // 기존에 작성한 클래스(PaperPrinter, FilmPrinter)를  변경할 필요는 없다.
  // 컴파일 오류가 발생하지 않는다.
}








