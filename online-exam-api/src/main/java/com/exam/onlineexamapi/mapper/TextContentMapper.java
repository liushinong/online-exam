package com.exam.onlineexamapi.mapper;

import com.exam.onlineexamapi.domain.entity.TextContent;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface TextContentMapper {
    Integer insert(TextContent textContent);
}
