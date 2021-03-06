package ch18.f;

// 자동차의 기본 기능을 갖추기 위해 AbstractCar 를 상속 받는다.
// => AbstractCar는 자동차가 가져야 할 기본 필드와 메서드를 갖고 있다.
public class Truck extends AbstractCar {
  
  // CarSpec에 선언된 on(), off() 메서드는 수퍼 클래스에서 미리 구현했기 때문에
  // 서브 클래스에서 다시 구현할 필요가 없어 편하다.
  //
  // 서브 클래스는 수퍼 클래스가 구현하지 않은 나머지 메서드만 구현하면 된다.
  @Override
  public void run() {
    System.out.println("덜컹 덜컹 달린다!");
  }
}
