package com.exam.onlineexamapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamPaper {

  private Integer id;
  private String name;
  private Integer subjectId;
  private Integer paperType;
  private Integer gradeLevel;
  private Integer score;
  private Integer questionCount;
  private Integer suggestTime;
  private java.sql.Timestamp limitStartTime;
  private java.sql.Timestamp limitEndTime;
  private Integer frameTextContentId;
  private Integer createUser;
  private java.sql.Timestamp createTime;
  private Integer deleted;
  private Integer taskExamId;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public Integer getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(Integer subjectId) {
    this.subjectId = subjectId;
  }


  public Integer getPaperType() {
    return paperType;
  }

  public void setPaperType(Integer paperType) {
    this.paperType = paperType;
  }


  public Integer getGradeLevel() {
    return gradeLevel;
  }

  public void setGradeLevel(Integer gradeLevel) {
    this.gradeLevel = gradeLevel;
  }


  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }


  public Integer getQuestionCount() {
    return questionCount;
  }

  public void setQuestionCount(Integer questionCount) {
    this.questionCount = questionCount;
  }


  public Integer getSuggestTime() {
    return suggestTime;
  }

  public void setSuggestTime(Integer suggestTime) {
    this.suggestTime = suggestTime;
  }


  public java.sql.Timestamp getLimitStartTime() {
    return limitStartTime;
  }

  public void setLimitStartTime(java.sql.Timestamp limitStartTime) {
    this.limitStartTime = limitStartTime;
  }


  public java.sql.Timestamp getLimitEndTime() {
    return limitEndTime;
  }

  public void setLimitEndTime(java.sql.Timestamp limitEndTime) {
    this.limitEndTime = limitEndTime;
  }


  public Integer getFrameTextContentId() {
    return frameTextContentId;
  }

  public void setFrameTextContentId(Integer frameTextContentId) {
    this.frameTextContentId = frameTextContentId;
  }


  public Integer getCreateUser() {
    return createUser;
  }

  public void setCreateUser(Integer createUser) {
    this.createUser = createUser;
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


  public Integer getTaskExamId() {
    return taskExamId;
  }

  public void setTaskExamId(Integer taskExamId) {
    this.taskExamId = taskExamId;
  }

}
