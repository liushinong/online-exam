package com.exam.onlineexamapi.mapper;

import com.exam.onlineexamapi.domain.entity.ExamPaperAnswer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExamPaperAnswerMapper {
    ExamPaperAnswer getByPidUid(Integer pid, Integer uid);
    Integer insertSelective(ExamPaperAnswer examPaperAnswer);
}
