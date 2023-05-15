package com.exam.onlineexamapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {

  private long id;
  private long questionType;
  private long subjectId;
  private long score;
  private long gradeLevel;
  private long difficult;
  private String correct;
  private long infoTextContentId;
  private long createUser;
  private long status;
  private java.sql.Timestamp createTime;
  private long deleted;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getQuestionType() {
    return questionType;
  }

  public void setQuestionType(long questionType) {
    this.questionType = questionType;
  }


  public long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(long subjectId) {
    this.subjectId = subjectId;
  }


  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }


  public long getGradeLevel() {
    return gradeLevel;
  }

  public void setGradeLevel(long gradeLevel) {
    this.gradeLevel = gradeLevel;
  }


  public long getDifficult() {
    return difficult;
  }

  public void setDifficult(long difficult) {
    this.difficult = difficult;
  }


  public String getCorrect() {
    return correct;
  }

  public void setCorrect(String correct) {
    this.correct = correct;
  }


  public long getInfoTextContentId() {
    return infoTextContentId;
  }

  public void setInfoTextContentId(long infoTextContentId) {
    this.infoTextContentId = infoTextContentId;
  }


  public long getCreateUser() {
    return createUser;
  }

  public void setCreateUser(long createUser) {
    this.createUser = createUser;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public long getDeleted() {
    return deleted;
  }

  public void setDeleted(long deleted) {
    this.deleted = deleted;
  }

}
