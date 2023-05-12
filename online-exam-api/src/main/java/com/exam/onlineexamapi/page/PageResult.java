package com.exam.onlineexamapi.page;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResult {
    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 总数
     */
    private long totalSize;

    /**
     * 总页码
     */
    private int totalPage;

    /**
     * 分页数据
     */
    private List<?> content;
}
