package com.exam.onlineexamapi.mapper;

import com.exam.onlineexamapi.domain.entity.TextContent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TextContentMapper {
    Integer insert(TextContent textContent);

    TextContent findById(Long id);

    Integer updateById(TextContent textContent);
}
