package com.eomcs.lms.domain;

import java.sql.Date;

public class Member {
  private int no;
  private String name;
  private String email;
  private int password;
  private String picture;
  private int phoneNum;
  private Date joinDate;
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public int getPassword() {
    return password;
  }
  public void setPassword(int password) {
    this.password = password;
  }
  public String getPicture() {
    return picture;
  }
  public void setPicture(String picture) {
    this.picture = picture;
  }
  public int getPhoneNum() {
    return phoneNum;
  }
  public void setPhoneNum(int phoneNum) {
    this.phoneNum = phoneNum;
  }
  public Date getJoinDate() {
    return joinDate;
  }
  public void setJoinDate(Date joinDate) {
    this.joinDate = joinDate;
  }

}
