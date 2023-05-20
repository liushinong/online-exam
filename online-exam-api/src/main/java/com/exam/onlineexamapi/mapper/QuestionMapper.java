package com.exam.onlineexamapi.mapper;

import com.exam.onlineexamapi.domain.entity.Question;
import com.exam.onlineexamapi.page.PageRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {
    Integer insert(Question question);

    Question selectById(Integer id);

    Integer updateById(Question question);

    Integer deleteById(Integer id);

    List<Question> findPageByTeacher(Integer teacherId);

    List<Question> findPageByTypeAndSubject(Integer questionType, Integer subjectId, Integer teacherId);

    List<Question> findPageByType(Integer questionType, Integer teacherId);

    List<Question> findPageBySubject(Integer subjectId, Integer teacherId);
}
