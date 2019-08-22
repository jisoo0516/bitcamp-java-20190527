package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.PhotoLessonDao;
import com.eomcs.lms.domain.PhotoLesson;

public class PhotoLessonDaoImpl implements PhotoLessonDao {

  Connection con;

  public PhotoLessonDaoImpl(Connection con) {
    this.con = con;
  }


  @Override
  public int insert(PhotoLesson photoLesson) throws Exception {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate("insert into lms_photoLesson(sdt,edt,tot_hr,day_hr,titl,conts)"
          + " values('" + photoLesson.getStartDate() + "','" + photoLesson.getEndDate() + "',"
          + photoLesson.getTotalHours() + "," + photoLesson.getDayHours() + ",'" + photoLesson.getTitle() + "','"
          + photoLesson.getContents() + "')");
    }
  }

  @Override
  public List<PhotoLesson> findAll() throws Exception {
    try (

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select photoLesson_id, titl, sdt,edt,tot_hr "
            + " from lms_photoLesson " + " order by sdt desc")) {

      ArrayList<PhotoLesson> list = new ArrayList<>();

      while (rs.next()) {
        PhotoLesson photoLesson = new PhotoLesson();
        photoLesson.setNo(rs.getInt("photoLesson_id"));
        photoLesson.setTitle(rs.getString("titl"));
        photoLesson.setStartDate(rs.getDate("sdt"));
        photoLesson.setEndDate(rs.getDate("edt"));
        photoLesson.setTotalHours(rs.getInt("tot_hr"));

        list.add(photoLesson);

      }
      return list;
    }
  }


  @Override
  public PhotoLesson findBy(int no) throws Exception {
    try (

        Statement stmt = con.createStatement();
        ResultSet rs =
            stmt.executeQuery("select * " + " from lms_photoLesson " + " where photoLesson_id = " + no)) {

      if (rs.next()) {
        PhotoLesson photoLesson = new PhotoLesson();
        photoLesson.setNo(rs.getInt("photoLesson_id"));
        photoLesson.setTitle(rs.getString("titl"));
        photoLesson.setContents(rs.getString("conts"));
        photoLesson.setStartDate(rs.getDate("sdt"));
        photoLesson.setEndDate(rs.getDate("edt"));
        photoLesson.setTotalHours(rs.getInt("tot_hr"));
        photoLesson.setDayHours(rs.getInt("day_hr"));


        return photoLesson;

      } else {
        return null;
      }
    }
  }

  @Override
  public int update(PhotoLesson photoLesson) throws Exception {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate("update lms_photoLesson set" + " titl='" + photoLesson.getTitle()
          + "', conts='" + photoLesson.getContents() + "', sdt='" + photoLesson.getStartDate() + "', edt='"
          + photoLesson.getEndDate() + "', tot_hr=" + photoLesson.getTotalHours() + ", day_hr="
          + photoLesson.getDayHours() + " where photoLesson_id=" + photoLesson.getNo());
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate("delete from lms_photoLesson where photoLesson_id=" + no);


    }
  }

}
