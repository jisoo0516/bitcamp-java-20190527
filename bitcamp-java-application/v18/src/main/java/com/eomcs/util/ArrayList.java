package com.eomcs.util;

import java.util.Arrays;

public class ArrayList<E> {//<> -> type 파라미터!
  
  private static final int DEFAULT_CAPACITY = 100;
  
  private  Object[] list;
  private  int size = 0;

  public ArrayList() {
   this(DEFAULT_CAPACITY);
  }
  public ArrayList(int initialCapacity) {
    if(initialCapacity < 5 || initialCapacity > 10000)
      list = new Object[DEFAULT_CAPACITY];
    else 
    list = new Object[initialCapacity];
  }
  
  public void add(E obj) {// 타입도 변수로 받을 수 있음 -> 제네릭
    if(this.size == list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1); //1비트 이동한다. 즉 2의 1승으로 oldCapacity를 나누는 것.
      list = Arrays.copyOf(this.list, newCapacity);
    }
    
    this.list[this.size] = obj;
  }
  
  public Object[] toArray() {
    return Arrays.copyOf(this.list, this.size);//new Object[this.size]
    
  }
  
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] a) {
    if(a.length < size) {
      //파라미터로 넘겨 받은 배열의 크기가 저장된 데이터의 개수보다 작다면
      //이 메서드에서 새 배열을 만든다.
      return (E[]) Arrays.copyOf(list, size, a.getClass()); // 세 번째 파라미터로 지정한 타입의 배열이 생성된다.
    }
    System.arraycopy(this.list, 0, a, 0, size);
    if(a.length > size)
      a[size] = null;
    return a;
  }
  
  public int size() {
    return this.size;
  }
}
