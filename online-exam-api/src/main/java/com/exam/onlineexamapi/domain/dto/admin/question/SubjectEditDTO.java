package com.exam.onlineexamapi.domain.dto.admin.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectEditDTO {
    private Integer id;
    private String name;
    private String img;
    private Integer teacherId;
}
