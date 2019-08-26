package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.util.DataSource;

public class PhotoBoardDaoImpl implements PhotoBoardDao {


  DataSource dataSource;

  public PhotoBoardDaoImpl(DataSource conFactory) {
    this.dataSource = conFactory;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "insert into lms_photo(lesson_id,titl)"
            + " values(?,?)",
            Statement.RETURN_GENERATED_KEYS)) {

      stmt.setInt(1, photoBoard.getLessonNo());
      stmt.setString(2, photoBoard.getTitle());
      
      int count = stmt.executeUpdate();
      try (ResultSet rs = stmt.getGeneratedKeys();) {
        if (rs.next()) {
          int autoIncrementPK = rs.getInt(1);
          photoBoard.setNo(autoIncrementPK);
        }
      }
      return count;
    }
  }

  @Override
  public List<PhotoBoard> findAll() throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select * from lms_photo order by photo_id desc")) {
      try(ResultSet rs =
          stmt.executeQuery()) {

        ArrayList<PhotoBoard> list = new ArrayList<>();

        while (rs.next()) {
          PhotoBoard photoBoard = new PhotoBoard();
          photoBoard.setNo(rs.getInt("photo_id"));
          photoBoard.setTitle(rs.getString("titl"));
          photoBoard.setReportingDate(rs.getDate("cdt"));
          photoBoard.setHits(rs.getInt("vw_cnt"));
          photoBoard.setLessonNo(rs.getInt("lesson_id"));
          list.add(photoBoard);

        }
        return list;
      }
    }
  }


  @Override
  public PhotoBoard findBy(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select * from lms_photo where photo_id = " + no)) {
      try(ResultSet rs =
          stmt.executeQuery()) {

        if (rs.next()) {
          PhotoBoard photoBoard = new PhotoBoard();
          photoBoard.setNo(rs.getInt("photo_id"));
          photoBoard.setTitle(rs.getString("titl"));
          photoBoard.setReportingDate(rs.getDate("cdt"));
          photoBoard.setHits(rs.getInt("vw_cnt"));
          photoBoard.setLessonNo(rs.getInt("lesson_id"));

          // 게시글 찾았으면 조회수를 증가시킨다.
          stmt.executeUpdate(
              "update lms_photo set" + " vw_cnt = vw_cnt + 1 where photo_id=" + no);

          return photoBoard;

        } else {
          return null;
        }
      }
    }
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "update lms_photo set" + " titl =?"
                + "where photo_id=?")) {  
      
      stmt.setString(2, photoBoard.getTitle());
      stmt.setInt(1, photoBoard.getNo());

      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "delete from lms_photo where photo_id=" + no)) {

      return stmt.executeUpdate();


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
