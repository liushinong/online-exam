package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.entity.TextContent;
import com.exam.onlineexamapi.mapper.TextContentMapper;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;
import com.exam.onlineexamapi.service.TextContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TextContentServiceImpl implements TextContentService {

    @Resource
    TextContentMapper textContentMapper;

    @Override
    public int save(TextContent record) {
        return textContentMapper.insert(record);
    }

    @Override
    public int delete(TextContent record) {
        return 0;
    }

    @Override
    public int delete(List<TextContent> records) {
        return 0;
    }

    @Override
    public TextContent findById(Long id) {
        return null;
    }

    @Override
    public PageResult findByPage(PageRequest pageRequest) {
        return null;
    }
}
