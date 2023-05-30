package com.exam.onlineexamapi.domain.dto.admin.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadResultVM {
    private String original;
    private String name;
    private String url;
    private Long size;
    private String type;
    private String state;
}
