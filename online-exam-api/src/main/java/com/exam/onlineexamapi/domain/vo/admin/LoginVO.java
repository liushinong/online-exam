package com.exam.onlineexamapi.domain.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginVO {
    private Long userId;
    private String token;
    private String username;
}
