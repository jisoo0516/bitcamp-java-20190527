package com.eomcs.lms.domain;

import java.sql.Date;

public class Board {
  private int no;
  private String contents;
  private Date reportingDate;
  private int hits;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
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

}
