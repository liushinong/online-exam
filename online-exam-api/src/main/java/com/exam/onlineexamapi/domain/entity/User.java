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

  private Integer id;
  private Integer userUuid;
  private String userName;
  private String realName;
  private String password;
  private String salt;
  private String birthDay;
  private Integer userLevel;
  private Integer age;
  private Integer sex;
  private String phone;
  private Integer role;
  private Integer status;
  private String imagePath;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp modifyTime;
  private java.sql.Timestamp lastActiveTime;
  private Integer deleted;
  private String wxOpenId;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getUserUuid() {
    return userUuid;
  }

  public void setUserUuid(Integer userUuid) {
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


  public Integer getUserLevel() {
    return userLevel;
  }

  public void setUserLevel(Integer userLevel) {
    this.userLevel = userLevel;
  }


  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }


  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }


  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
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


  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }


  public String getWxOpenId() {
    return wxOpenId;
  }

  public void setWxOpenId(String wxOpenId) {
    this.wxOpenId = wxOpenId;
  }

}
