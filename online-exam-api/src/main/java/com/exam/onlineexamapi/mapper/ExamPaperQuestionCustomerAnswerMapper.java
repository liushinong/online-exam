package com.exam.onlineexamapi.mapper;

import com.exam.onlineexamapi.domain.entity.ExamPaperQuestionCustomerAnswer;
import com.exam.onlineexamapi.domain.other.ExamPaperAnswerUpdate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamPaperQuestionCustomerAnswerMapper {
    Integer insertList(List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers);
    List<ExamPaperQuestionCustomerAnswer> selectListByPaperAnswerId(Integer id);
    Integer updateScore(List<ExamPaperAnswerUpdate> examPaperAnswerUpdates);
    List<ExamPaperQuestionCustomerAnswer> findPageBySubjectAndUser(Integer subjectId, Integer userId);
    List<ExamPaperQuestionCustomerAnswer> findPageByUser(Integer userId);
}
