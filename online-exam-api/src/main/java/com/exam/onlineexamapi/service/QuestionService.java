package com.exam.onlineexamapi.service;

import com.exam.onlineexamapi.domain.dto.admin.question.QuestionEditRequestDTO;
import com.exam.onlineexamapi.domain.entity.Question;

public interface QuestionService extends CurdService<Question>{

    Integer insertQuestion(QuestionEditRequestDTO model);
}
