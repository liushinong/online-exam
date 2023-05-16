package com.exam.onlineexamapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {

  private long id;
  private Integer questionType;
  private Long subjectId;
  private Integer score;
  private Integer gradeLevel;
  private Integer difficult;
  private String correct;
  private long infoTextContentId;
  private long createUser;
  private Integer status;
  private java.sql.Timestamp createTime;
  private Integer deleted;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public Integer getQuestionType() {
    return questionType;
  }

  public void setQuestionType(Integer questionType) {
    this.questionType = questionType;
  }


  public Long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(Long subjectId) {
    this.subjectId = subjectId;
  }


  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }


  public Integer getGradeLevel() {
    return gradeLevel;
  }

  public void setGradeLevel(Integer gradeLevel) {
    this.gradeLevel = gradeLevel;
  }


  public Integer getDifficult() {
    return difficult;
  }

  public void setDifficult(Integer difficult) {
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


  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }

  /**
   * correct 转换
   * @param correct
   * @param correctArray
   */
  public void setCorrectFromVM(String correct, List<String> correctArray) {
    if (this.getQuestionType() == 2) {
      String correctJoin = correctArray.stream().sorted().collect(Collectors.joining(","));
      this.setCorrect(correctJoin);
    } else {
      this.setCorrect(correct);
    }
  }

}
