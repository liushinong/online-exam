package com.exam.onlineexamapi.mapper;

import com.exam.onlineexamapi.domain.entity.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    Integer insert(Question question);
}
