package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.DO.question.QuestionItemObject;
import com.exam.onlineexamapi.domain.DO.question.QuestionObject;
import com.exam.onlineexamapi.domain.dto.admin.question.QuestionEditItemRequestDTO;
import com.exam.onlineexamapi.domain.dto.admin.question.QuestionEditRequestDTO;
import com.exam.onlineexamapi.domain.entity.Question;
import com.exam.onlineexamapi.domain.entity.TextContent;
import com.exam.onlineexamapi.domain.enums.QuestionTypeEnum;
import com.exam.onlineexamapi.domain.vo.admin.QuestionVO;
import com.exam.onlineexamapi.mapper.QuestionMapper;
import com.exam.onlineexamapi.page.MybatisPageHelper;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;
import com.exam.onlineexamapi.service.QuestionService;
import com.exam.onlineexamapi.service.TextContentService;
import com.exam.onlineexamapi.utils.ExamUtil;
import com.exam.onlineexamapi.utils.JsonUtil;
import com.exam.onlineexamapi.utils.ModelMapperSingle;
import org.modelmapper.ModelMapper;
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

    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();

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

    @Override
    public QuestionEditRequestDTO getQuestionEditRequestVM(Integer id) {
        Question question = questionMapper.selectById(id);
        return getQuestionEditRequestVM(question);
    }

    @Override
    public QuestionEditRequestDTO getQuestionEditRequestVM(Question question) {
        // 题目映射
        TextContent questionInfoTextContent = textContentService.findById(question.getInfoTextContentId());
        QuestionObject questionObject = JsonUtil.toJsonObject(questionInfoTextContent.getContent(), QuestionObject.class);
        QuestionEditRequestDTO questionEditRequestDTO = modelMapper.map(question, QuestionEditRequestDTO.class);
        questionEditRequestDTO.setTitle(questionObject.getTitleContent());

        // 答案
        QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.fromCode(question.getQuestionType());
        switch (questionTypeEnum) {
            case SingleChoice:
            case TrueFalse:
                questionEditRequestDTO.setCorrect(question.getCorrect());
                break;
            case MultipleChoice:
                questionEditRequestDTO.setCorrectArray(ExamUtil.contentToArray(question.getCorrect()));
                break;
            case GapFilling:
                List<String> correctContent = questionObject.getQuestionItemObjectList().stream().map(d -> d.getContent()).collect(Collectors.toList());
                questionEditRequestDTO.setCorrectArray(correctContent);
                break;
            case ShortAnswer:
                questionEditRequestDTO.setCorrect(question.getCorrect());
                break;
            default:
                break;
        }
        questionEditRequestDTO.setScore(String.valueOf(question.getScore()));
        questionEditRequestDTO.setAnalyze(questionObject.getAnalyze());

        // 题目项映射
        List<QuestionEditItemRequestDTO> editItems = questionObject.getQuestionItemObjectList().stream().map(o -> {
            QuestionEditItemRequestDTO questionItemObject = modelMapper.map(o, QuestionEditItemRequestDTO.class);
            if (o.getScore() != null) {
                questionItemObject.setScore(String.valueOf(o.getScore()));
            }
            return questionItemObject;
        }).collect(Collectors.toList());
        questionEditRequestDTO.setItems(editItems);
        return questionEditRequestDTO;
    }

    @Override
    public Integer delQuestion(Integer id) {
        return questionMapper.deleteById(id);
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
        return 1;
    }

    @Override
    public int delete(List<Question> records) {
        return 0;
    }

    @Override
    public Question findById(Integer id) {
        return null;
    }

    @Override
    public PageResult findByPage(PageRequest pageRequest) {
        PageResult pageResult = null;
        Object questionType = pageRequest.getParam("questionType");
        Object subjectId = pageRequest.getParam("subjectId");
        Object teacherId = pageRequest.getParam("teacherId");
        if (questionType != null && subjectId != null) {
            pageResult = MybatisPageHelper.findByPage(pageRequest, questionMapper, "findPageByTypeAndSubject", questionType, subjectId, teacherId);
        } else if (questionType != null) {
            pageResult = MybatisPageHelper.findByPage(pageRequest, questionMapper, "findPageByType", questionType, teacherId);
        } else if (subjectId != null) {
            pageResult = MybatisPageHelper.findByPage(pageRequest, questionMapper, "findPageBySubject", subjectId, teacherId);
        }
        else {
            pageResult = MybatisPageHelper.findByPage(pageRequest, questionMapper, "findPageByTeacher", teacherId);
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
