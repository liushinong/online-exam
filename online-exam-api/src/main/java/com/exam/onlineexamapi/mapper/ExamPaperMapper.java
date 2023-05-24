package com.exam.onlineexamapi.mapper;

import com.exam.onlineexamapi.domain.entity.ExamPaper;
import com.exam.onlineexamapi.page.PageResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamPaperMapper {
    Integer insertSelective(ExamPaper examPaper);
    ExamPaper selectByPrimaryKey(Integer id);
    Integer updateByPrimaryKeySelective(ExamPaper examPaper);
    Integer deleteById(Integer id);
    List<ExamPaper> findPageBySubjectAndTeacher(Integer subjectId, Integer teacherId);
    List<ExamPaper> findPageByTeacher(Integer teacherId);
    List<ExamPaper> studentPageBySubjectAndType(Integer subjectId, Integer paperType, Integer userId);
    List<ExamPaper> studentPageByType(Integer paperType, Integer userId);
}
