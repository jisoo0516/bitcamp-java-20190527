package com.eomcs.lms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {


  SqlSessionFactory sqlSessionFactory;


  public PhotoFileDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(PhotoFile photoFile) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.insert("PhotoFileDao.insert", photoFile);
    }
  }

  @Override
  public List<PhotoFile> findAll(int boardNo) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("PhotoFileDao.findAll", boardNo);
    }
  }




  @Override
  public int deleteAll(int boardNo) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("PhotoFileDao.deleteAll", boardNo);
    }
    }
  }

//  public static void main(String[] args) throws Exception {
//    try 
//    (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111");) {
//      PhotoFileDao dao = new PhotoFileDaoImpl(con);
//     
//      // 1) insert() 테스트
//     /*
//      PhotoFile b = new PhotoFile();
//      b.setBoardNo(6);
//      b.setFilePath("ok5.gif");
//
//      dao.insert(b);
//      
//      */
//      // 2) findAll() 테스트
//      /*
//      List<PhotoFile> list = dao.findAll(6);
//      for(PhotoFile b : list) {
//        System.out.println(b);
//      }
//       
//      */
//   
//      // 3) delete() 테스트
//      /*
//      dao.deleteAll(6);
//       */
//      System.out.println("실행 완료!");
//    }
//
//  }

