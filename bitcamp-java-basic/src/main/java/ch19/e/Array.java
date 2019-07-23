// 배열 복제를 도와주는 클래스
package ch19.e;

public class Array {
  LinkedList list;
  
  public Array(LinkedList list) {
    this.list = list;
  }
  
  // 입력된 순서대로 배열을 만든다.
  public Object[] copy() {
    Object[] arr = new Object[list.size()];
    
    for (int i = 0; i < list.size(); i++) {
      arr[i] = list.get(i);
    }
    return arr;
  }
  
  // 입력된 순서의 반대로 배열을 만든다.
  public Object[] reverseCopy() {
    Object[] arr = new Object[list.size()];
    
    for (int i = list.size() - 1, j = 0; i >= 0; i--, j++) {
      arr[j] = list.get(i);
    }
    
    return arr;
  }
}

// 현재 LinkedList와 Array 클래스가 상호교차되어있는데 이 관계는 최악의 설계로
// 그저 중첩 클래스 설명을 위해 어쩔 수 없이 사용하였음....



