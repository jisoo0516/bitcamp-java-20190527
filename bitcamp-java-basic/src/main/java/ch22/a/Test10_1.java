// java.io.File 클래스 : 필터 사용하기
package ch22.a;

import java.io.File;
import java.io.FilenameFilter;

public class Test10_1 {

  // static nested class
  static class TextFileFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
      if (name.endsWith(".txt"))
        return true;
      else 
        return false;
    }
  }
  
  public static void main(String[] args) throws Exception {
    
    File file = new File(".");
    
    String[] names = file.list(new TextFileFilter());
    
    for (String name : names) {
      System.out.println(name);
    }
    
  }    
}









