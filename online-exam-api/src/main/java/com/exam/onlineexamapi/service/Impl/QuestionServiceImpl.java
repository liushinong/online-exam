package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.DO.question.QuestionItemObject;
import com.exam.onlineexamapi.domain.DO.question.QuestionObject;
import com.exam.onlineexamapi.domain.dto.admin.question.QuestionEditRequestDTO;
import com.exam.onlineexamapi.domain.entity.Question;
import com.exam.onlineexamapi.domain.entity.TextContent;
import com.exam.onlineexamapi.mapper.QuestionMapper;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;
import com.exam.onlineexamapi.service.QuestionService;
import com.exam.onlineexamapi.service.TextContentService;
import com.exam.onlineexamapi.utils.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    QuestionMapper questionMapper;

    @Resource
    TextContentService textContentService;

    @Override
    @Transactional
    public Integer insertQuestion(QuestionEditRequestDTO model) {
        TextContent textContent = new TextContent();
        textContent.setCreateTime(new Timestamp(new Date().getTime()));
        setQuestionInfoFromVM(textContent, model);
        textContentService.save(textContent);

        String correct = "";
        if (model.getQuestionType() == 2) {
            correct = model.getCorrectArray().stream().sorted().collect(Collectors.joining(","));
        } else {
            correct = model.getCorrect();
        }

        Question question = Question.builder()
                .questionType(model.getQuestionType())
                .subjectId(model.getSubjectId())
                .score((int)Float.parseFloat(model.getScore()))
                .gradeLevel(1)
                .difficult(model.getDifficult())
                .correct(correct)
                .infoTextContentId(textContent.getId())
                .createUser(model.getUserId())
                .build();
        return questionMapper.insert(question);
    }

    /**
     * 题干，解析等info插入到content表
     * @param textContent
     * @param model
     */
    public void setQuestionInfoFromVM(TextContent textContent, QuestionEditRequestDTO model) {
        List<QuestionItemObject> itemObjects = model.getItems().stream().map(i -> {
            QuestionItemObject itemObject = QuestionItemObject.builder()
                    .prefix(i.getPrefix())
                    .content(i.getContent())
                    .score(i.getScore())
                    .build();
            return itemObject;
        }).collect(Collectors.toList());
        String correct = "";
        if (model.getQuestionType() == 2) {
            correct = model.getCorrectArray().stream().sorted().collect(Collectors.joining(","));
        } else {
            correct = model.getCorrect();
        }
        QuestionObject questionObject = QuestionObject.builder()
                .titleContent(model.getTitle())
                .analyze(model.getAnalyze())
                .questionItemObjectList(itemObjects)
                .correct(correct)
                .build();
        textContent.setContent(JsonUtil.toJsonStr(questionObject));
    }

    @Override
    public int save(Question record) {
        return 0;
    }

    @Override
    public int delete(Question record) {
        return 0;
    }

    @Override
    public int delete(List<Question> records) {
        return 0;
    }

    @Override
    public Question findById(Long id) {
        return null;
    }

    @Override
    public PageResult findByPage(PageRequest pageRequest) {
        return null;
    }
}
