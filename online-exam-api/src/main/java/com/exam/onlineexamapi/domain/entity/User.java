package com.exam.onlineexamapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

  private long id;
  private long userUuid;
  private String userName;
  private String realName;
  private String password;
  private String salt;
  private String birthDay;
  private long userLevel;
  private long age;
  private long sex;
  private String phone;
  private long role;
  private long status;
  private String imagePath;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp modifyTime;
  private java.sql.Timestamp lastActiveTime;
  private long deleted;
  private String wxOpenId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUserUuid() {
    return userUuid;
  }

  public void setUserUuid(long userUuid) {
    this.userUuid = userUuid;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getBirthDay() {
    return birthDay;
  }

  public void setBirthDay(String birthDay) {
    this.birthDay = birthDay;
  }


  public long getUserLevel() {
    return userLevel;
  }

  public void setUserLevel(long userLevel) {
    this.userLevel = userLevel;
  }


  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }


  public long getSex() {
    return sex;
  }

  public void setSex(long sex) {
    this.sex = sex;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public long getRole() {
    return role;
  }

  public void setRole(long role) {
    this.role = role;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getModifyTime() {
    return modifyTime;
  }

  public void setModifyTime(java.sql.Timestamp modifyTime) {
    this.modifyTime = modifyTime;
  }


  public java.sql.Timestamp getLastActiveTime() {
    return lastActiveTime;
  }

  public void setLastActiveTime(java.sql.Timestamp lastActiveTime) {
    this.lastActiveTime = lastActiveTime;
  }


  public long getDeleted() {
    return deleted;
  }

  public void setDeleted(long deleted) {
    this.deleted = deleted;
  }


  public String getWxOpenId() {
    return wxOpenId;
  }

  public void setWxOpenId(String wxOpenId) {
    this.wxOpenId = wxOpenId;
  }

}
