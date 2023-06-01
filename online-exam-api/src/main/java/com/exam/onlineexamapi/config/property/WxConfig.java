package com.exam.onlineexamapi.config.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WxConfig {
    private String appid;
    private String secret;
    private Duration tokenToLive;
    private List<String> securityIgnoreUrls;
}
