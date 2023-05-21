package com.exam.onlineexamapi.service;

import com.exam.onlineexamapi.domain.dto.admin.question.QuestionEditRequestDTO;
import com.exam.onlineexamapi.domain.entity.Question;

public interface QuestionService extends CurdService<Question>{

    Integer insertQuestion(QuestionEditRequestDTO model);

    Integer updateQuestion(QuestionEditRequestDTO model);

    QuestionEditRequestDTO getQuestionEditRequestVM(Integer id);

    QuestionEditRequestDTO getQuestionEditRequestVM(Question question);

    Integer delQuestion(Integer id);
}
