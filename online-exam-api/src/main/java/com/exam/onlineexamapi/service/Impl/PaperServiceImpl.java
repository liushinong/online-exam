package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.entity.Paper;
import com.exam.onlineexamapi.mapper.PaperMapper;
import com.exam.onlineexamapi.page.MybatisPageHelper;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.service.PaperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

    @Resource
    PaperMapper paperMapper;

    @Override
    public int save(Paper record) {
        return 0;
    }

    @Override
    public int delete(Paper record) {
        return 0;
    }

    @Override
    public int delete(List<Paper> records) {
        return 0;
    }

    @Override
    public Paper findById(Integer id) {
        return null;
    }

    @Override
    public PageResult findByPage(PageRequest pageRequest) {
        return null;
    }

    @Override
    public PageResult findPageBySubjectId(PageRequest pageRequest) {
        Object subjectId = pageRequest.getParam("subjectId");
        return MybatisPageHelper.findByPage(pageRequest, paperMapper, "findPageBySubjectId", subjectId);
    }
}
