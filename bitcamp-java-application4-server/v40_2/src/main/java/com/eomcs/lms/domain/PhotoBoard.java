package com.eomcs.lms.domain;

import java.io.Serializable;
import java.sql.Date;

public class PhotoBoard implements Serializable {
  private static final long serialVersionUID = 1L;

  
  private int no;
  private String title;
  private Date reportingDate;
  private int hits;
  private int lessonNo;
  
  
  @Override
  public String toString() {
    return "PhotoBoard [no=" + no + ", title=" + title + ", reportingDate=" + reportingDate
        + ", hits=" + hits + ", lessonNo=" + lessonNo + "]";
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
