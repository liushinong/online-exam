package com.exam.onlineexamapi.mapper;

import com.exam.onlineexamapi.domain.entity.ExamPaperAnswer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamPaperAnswerMapper {
    ExamPaperAnswer getByPidUid(Integer pid, Integer uid);
    Integer insertSelective(ExamPaperAnswer examPaperAnswer);
    List<ExamPaperAnswer> findPageByTeacher(Integer teacherId);
    List<ExamPaperAnswer> findPageBySubjectAndTeacher(Integer subjectId, Integer teacherId);
    ExamPaperAnswer selectById(Integer id);
    Integer updateByPrimaryKeySelective(ExamPaperAnswer examPaperAnswer);
    List<ExamPaperAnswer> findPageByTeacherAndComplete(Integer teacherId);
    List<ExamPaperAnswer> findPageBySubjectAndTeacherAndComplete(Integer subjectId, Integer teacherId);
    List<ExamPaperAnswer> findPageByUser(Integer userId);
    List<ExamPaperAnswer> findPageBySubjectAndUser(Integer subjectId, Integer userId);
}
