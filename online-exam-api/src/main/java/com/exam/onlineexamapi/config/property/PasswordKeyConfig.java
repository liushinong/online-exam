package com.exam.onlineexamapi.config.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PasswordKeyConfig {
    private String publicKey;

    private String privateKey;
}
