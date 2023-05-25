package com.exam.onlineexamapi.domain.vo.admin;

import com.exam.onlineexamapi.domain.DO.question.QuestionObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionVO {
    private Integer id;
    private Integer questionType;
    private Integer subjectId;
    private Integer score;
    private Integer gradeLevel;
    private Integer difficult;
    private String correct;
    private QuestionObject questionObject;
    private Integer createUser;
    private Integer status;
    private Timestamp createTime;
}
