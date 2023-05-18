package com.exam.onlineexamapi.domain.vo.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaperVO {
    private Long id;
    private String name;
    private java.sql.Timestamp suggestTime;
    private java.sql.Timestamp limitStartTime;
    private java.sql.Timestamp limitEndTime;
    private Long score;
    private Long questionCount;
    private Long subjectName;
}
