package com.exam.onlineexamapi.domain.dto.student.subject;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class JoinSubjectDTO {
    private Integer userId;
    private String subjectCode;

}
