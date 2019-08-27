package com.eomcs.lms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {

  private static final long serialVersionUID = 1L;
  private int no;
  private String name;
  private String email;
  private String password;
  private String picture;
  private String phoneNum;
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
 
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getPicture() {
    return picture;
  }
  public void setPicture(String picture) {
    this.picture = picture;
  }
  
  public String getPhoneNum() {
    return phoneNum;
  }
  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }
  public Date getJoinDate() {
    return joinDate;
  }
  public void setJoinDate(Date joinDate) {
    this.joinDate = joinDate;
  }
  
  

}
