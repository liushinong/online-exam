package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.DO.paper.PaperSelectDO;
import com.exam.onlineexamapi.domain.entity.Paper;
import com.exam.onlineexamapi.mapper.PaperMapper;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.PaperService;

import java.util.List;

public class PaperServiceImpl implements PaperService {
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
    public Paper findById(Long id) {
        return null;
    }

    @Override
    public PageResult findByPage(PageRequest pageRequest) {
        return null;
    }

//    @Override
//    public RestResult paperSelect(Long subjectId) {
//        List<PaperSelectDO> list = PaperMapper.paperSelect(subjectId);
//        return new RestResultBuilder<>().success(list);
//    }
}
