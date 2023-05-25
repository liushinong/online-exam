package com.exam.onlineexamapi.domain.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailVo {
    private Integer questionNum;
    private Integer subjectNum;
    private Integer studentNum;
    private Integer paperNum;

}
