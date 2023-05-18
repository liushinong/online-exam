package com.exam.onlineexamapi.page;

import com.exam.onlineexamapi.utils.ReflectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分页查询封装
 */
public class MybatisPageHelper {

    public static final String FIND_BY_PAGE = "findByPage";

    /**
     * 分页查询，约定方法名为 findByPage
     * @param pageRequest 分页请求参数
     * @param mapper 对应mapper对象
     * @return
     */
    public static PageResult findByPage(PageRequest pageRequest, Object mapper) {
        return findByPage(pageRequest, mapper, FIND_BY_PAGE);
    }

    /**
     * 调用分页插件进行分页查询
     * @param pageRequest 分页参数
     * @param mapper 对应mapper对象
     * @param queryMethodName 要分页的查询方法名
     * @param args 方法参数
     * @return
     */
    public static PageResult findByPage(PageRequest pageRequest, Object mapper, String queryMethodName, Object... args) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        Object result = ReflectionUtils.invoke(mapper, queryMethodName, args);
        return getPageResult(pageRequest, new PageInfo<>((List) result));
    }

    /**
     * 封装分页返回结果
     * @param pageRequest
     * @param pageInfo
     * @return
     */
    public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalPage(pageInfo.getPages());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
