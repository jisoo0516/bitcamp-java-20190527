package com.eomcs.lms.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class PhotoBoard implements Serializable {
  private static final long serialVersionUID = 1L;

  
  private int no;
  private String title;
  private Date reportingDate;
  private int hits;
  private int lessonNo;
  
  // 자식 테이블 'lms_photo_file'의 데이터를 담을 PhotoFile 객체 목록.
  private List<PhotoFile> files;
  
  
  @Override
  public String toString() {
    return "PhotoBoard [no=" + no + ", title=" + title + ", reportingDate=" + reportingDate
        + ", hits=" + hits + ", lessonNo=" + lessonNo + ", files=" + files + "]";
  }
  
  public List<PhotoFile> getFiles() {
    return files;
  }
  public void setFiles(List<PhotoFile> files) {
    this.files = files;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public Date getReportingDate() {
    return reportingDate;
  }
  public void setReportingDate(Date reportingDate) {
    this.reportingDate = reportingDate;
  }
  public int getHits() {
    return hits;
  }
  public void setHits(int hits) {
    this.hits = hits;
  }
  public int getLessonNo() {
    return lessonNo;
  }
  public void setLessonNo(int lessonNo) {
    this.lessonNo = lessonNo;
  }
  
  
}
