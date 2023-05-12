package com.exam.onlineexamapi.service;

import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;

import java.util.List;

/**
 * 通用Curd接口
 */
public interface CurdService<T> {
    /**
     * 保存
     * @param record
     * @return
     */
    int save(T record);

    /**
     * 删除
     * @param record
     * @return
     */
    int delete(T record);

    /**
     * 批量删除
     * @param records
     * @return
     */
    int delete(List<T> records);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 分页查询
     * @param pageRequest
     * @return
     */
    PageResult findByPage(PageRequest pageRequest);
}
