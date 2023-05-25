package com.exam.onlineexamapi.domain.dto.admin.subject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectUserDTO {

    private Integer subjectId;
    private Integer userId;

}
