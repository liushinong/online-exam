package com.exam.onlineexamapi.domain.DO.paper;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaperSelectDO {
    private Long id;
    private Long subjectId;
    private Long paperType;
    private Long gradeLevel;
    private Long score;
    private Long questionCount;
    private Long suggetTime;
    private java.sql.Timestamp limitStartTime;
    private java.sql.Timestamp limitEndTime;
    private String name;
    private String subjectName;
}

