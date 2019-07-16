package com.eomcs.util;

import java.util.Arrays;

public class ArrayList {
  
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
  
  public void add(Object obj) {
    if(this.size == list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1); //1비트 이동한다. 즉 2의 1승으로 oldCapacity를 나누는 것.
      list = Arrays.copyOf(this.list, newCapacity);
    }
    
    this.list[this.size] = obj;
  }
  
  public Object [] toArray() {
    return Arrays.copyOf(this.list, this.size);
    
  }
}
