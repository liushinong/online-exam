package com.exam.onlineexamapi.domain.dto.admin.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UeditorConfigVM {
    private String imageActionName;
    private  String imageFieldName;
    private Long imageMaxSize;
    private List<String> imageAllowFiles;
    private boolean imageCompressEnable;
    private Integer imageCompressBorder;
    private String imageInsertAlign;
    private String imageUrlPrefix;
    private String imagePathFormat;
}
