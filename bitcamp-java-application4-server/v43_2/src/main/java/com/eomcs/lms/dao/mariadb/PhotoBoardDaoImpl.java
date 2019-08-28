package com.eomcs.lms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;

public class PhotoBoardDaoImpl implements PhotoBoardDao {

  SqlSessionFactory sqlSessionFactory;

  public PhotoBoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
           return sqlSession.insert("PhotoBoardDao.insert", photoBoard);
    }
  }

  @Override
  public List<PhotoBoard> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
        return sqlSession.selectList("PhotoBoardDao.findAll");
      
    }
  }


  @Override
  public PhotoBoard findBy(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
     PhotoBoard photoBoard = sqlSession.selectOne("PhotoBoardDao.findBy",no);
     if(photoBoard != null) {
       sqlSession.update("PhotoBoardDao.increaseViewCount", no);
       sqlSession.commit();
     }
     return photoBoard;
      
    }
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("PhotoBoardDao.update", photoBoard);

      
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("PhotoBoardDao.delete", no);



    }
  }
  //  public static void main(String[] args) throws Exception {
  //    try 
  //    (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111");) {
  //
  //      PhotoBoardDao dao = new PhotoBoardDaoImpl(con);
  //
  //      // 1) insert() 테스트
  //      /*
  //      PhotoBoard b = new PhotoBoard();
  //      b.setLessonNo(101);
  //      b.setTitle("사진 게시글 테스트2");
  //
  //      dao.insert(b);
  //       */
  //
  //      // 2) findAll() 테스트
  //      /*
  //      List<PhotoBoard> list = dao.findAll();
  //      for(PhotoBoard b : list) {
  //        System.out.println(b);
  //      }
  //       */
  //
  //      // 3) findBy() 테스트
  //      /*
  //      PhotoBoard b = dao.findBy(11);
  //      System.out.println(b);
  //       */
  //
  //      // 4) update() 테스트
  //      /*
  //      PhotoBoard b = new PhotoBoard();
  //      b.setNo(11);
  //      b.setTitle("제목 변경");
  //      dao.update(b);
  //       */
  //
  //      // 5) delete() 테스트
  //      /*
  //      dao.delete(11);
  //       */
  //      System.out.println("실행 완료!");
  //    }
  //
  //  }
}
