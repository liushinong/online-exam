package com.exam.onlineexamapi.domain.entity;


public class Paper {

  private long id;
  private String name;
  private long subjectId;
  private long paperType;
  private long gradeLevel;
  private long score;
  private long questionCount;
  private long suggestTime;
  private java.sql.Timestamp limitStartTime;
  private java.sql.Timestamp limitEndTime;
  private long frameTextContentId;
  private long createUser;
  private java.sql.Timestamp createTime;
  private long deleted;
  private long taskExamId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(long subjectId) {
    this.subjectId = subjectId;
  }


  public long getPaperType() {
    return paperType;
  }

  public void setPaperType(long paperType) {
    this.paperType = paperType;
  }


  public long getGradeLevel() {
    return gradeLevel;
  }

  public void setGradeLevel(long gradeLevel) {
    this.gradeLevel = gradeLevel;
  }


  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }


  public long getQuestionCount() {
    return questionCount;
  }

  public void setQuestionCount(long questionCount) {
    this.questionCount = questionCount;
  }


  public long getSuggestTime() {
    return suggestTime;
  }

  public void setSuggestTime(long suggestTime) {
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


  public long getFrameTextContentId() {
    return frameTextContentId;
  }

  public void setFrameTextContentId(long frameTextContentId) {
    this.frameTextContentId = frameTextContentId;
  }


  public long getCreateUser() {
    return createUser;
  }

  public void setCreateUser(long createUser) {
    this.createUser = createUser;
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


  public long getTaskExamId() {
    return taskExamId;
  }

  public void setTaskExamId(long taskExamId) {
    this.taskExamId = taskExamId;
  }

}
