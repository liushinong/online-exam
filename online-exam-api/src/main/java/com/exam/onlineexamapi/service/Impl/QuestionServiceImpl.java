package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.DO.question.QuestionItemObject;
import com.exam.onlineexamapi.domain.DO.question.QuestionObject;
import com.exam.onlineexamapi.domain.dto.admin.question.QuestionEditRequestDTO;
import com.exam.onlineexamapi.domain.entity.Question;
import com.exam.onlineexamapi.domain.entity.TextContent;
import com.exam.onlineexamapi.domain.vo.admin.QuestionVO;
import com.exam.onlineexamapi.mapper.QuestionMapper;
import com.exam.onlineexamapi.page.MybatisPageHelper;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;
import com.exam.onlineexamapi.service.QuestionService;
import com.exam.onlineexamapi.service.TextContentService;
import com.exam.onlineexamapi.utils.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    QuestionMapper questionMapper;

    @Resource
    TextContentService textContentService;

    /**
     * 插入题目
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Integer insertQuestion(QuestionEditRequestDTO model) {
        TextContent textContent = new TextContent();
        textContent.setCreateTime(new Timestamp(new Date().getTime()));
        setQuestionInfoFromVM(textContent, model);
        textContentService.save(textContent);

        Question question = Question.builder()
                .questionType(model.getQuestionType())
                .subjectId(model.getSubjectId())
                .score((int) Float.parseFloat(model.getScore()))
                .gradeLevel(1)
                .difficult(model.getDifficult())
                .infoTextContentId(textContent.getId())
                .createUser(model.getUserId())
                .build();
        question.setCorrectFromVM(model.getCorrect(), model.getCorrectArray());
        return questionMapper.insert(question);
    }

    @Override
    @Transactional
    public Integer updateQuestion(QuestionEditRequestDTO model) {
        Question question = questionMapper.selectById(model.getId());
        question.setQuestionType(model.getQuestionType());
        question.setSubjectId(model.getSubjectId());
        question.setScore((int) Float.parseFloat(model.getScore()));
        question.setDifficult(model.getDifficult());
        question.setCorrectFromVM(model.getCorrect(), model.getCorrectArray());
        questionMapper.updateById(question);

        TextContent textContent = textContentService.findById(question.getInfoTextContentId());
        setQuestionInfoFromVM(textContent, model);
        return textContentService.updateById(textContent);
    }

    /**
     * 题干，解析等info插入到content表
     *
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
        return questionMapper.deleteById(record.getId());
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
        PageResult pageResult = null;
        Object questionType = pageRequest.getParam("questionType");
        if (questionType != null) {
            pageResult = MybatisPageHelper.findByPage(pageRequest, questionMapper, "findPageByType", questionType);
        } else {
            pageResult = MybatisPageHelper.findByPage(pageRequest, questionMapper);
        }
        List<Question> list = (List<Question>) pageResult.getContent();
        List<QuestionVO> listVO = new ArrayList<>();
        list.forEach(i -> {
            TextContent textContent = textContentService.findById(i.getInfoTextContentId());
            QuestionObject questionObject = JsonUtil.toJsonObject(textContent.getContent(), QuestionObject.class);
            listVO.add(
                    QuestionVO.builder()
                            .id(i.getId())
                            .questionType(i.getQuestionType())
                            .subjectId(i.getSubjectId())
                            .score(i.getScore())
                            .gradeLevel(i.getGradeLevel())
                            .difficult(i.getDifficult())
                            .correct(i.getCorrect())
                            .questionObject(questionObject)
                            .createUser(i.getCreateUser())
                            .status(i.getStatus())
                            .createTime(i.getCreateTime())
                            .build()
            );
        });
        pageResult.setContent(listVO);
        return pageResult;
    }
}
