package com.exam.onlineexamapi.config.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QnConfig {
    private String url;
    private String bucket;
    private String accessKey;
    private String secretKey;
}
