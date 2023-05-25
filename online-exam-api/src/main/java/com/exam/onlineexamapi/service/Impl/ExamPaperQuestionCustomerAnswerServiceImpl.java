package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.entity.ExamPaperQuestionCustomerAnswer;
import com.exam.onlineexamapi.mapper.ExamPaperQuestionCustomerAnswerMapper;
import com.exam.onlineexamapi.service.ExamPaperQuestionCustomerAnswerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExamPaperQuestionCustomerAnswerServiceImpl implements ExamPaperQuestionCustomerAnswerService {

    @Resource
    ExamPaperQuestionCustomerAnswerMapper examPaperQuestionCustomerAnswerMapper;

    @Override
    public void insertList(List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers) {
        examPaperQuestionCustomerAnswerMapper.insertList(examPaperQuestionCustomerAnswers);
    }
}
