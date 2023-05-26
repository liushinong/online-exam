package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.dto.student.exam.ExamPaperSubmitItemVM;
import com.exam.onlineexamapi.domain.entity.ExamPaperQuestionCustomerAnswer;
import com.exam.onlineexamapi.domain.entity.Subject;
import com.exam.onlineexamapi.domain.entity.TextContent;
import com.exam.onlineexamapi.domain.enums.QuestionTypeEnum;
import com.exam.onlineexamapi.domain.other.ExamPaperAnswerUpdate;
import com.exam.onlineexamapi.mapper.ExamPaperQuestionCustomerAnswerMapper;
import com.exam.onlineexamapi.page.MybatisPageHelper;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;
import com.exam.onlineexamapi.service.ExamPaperQuestionCustomerAnswerService;
import com.exam.onlineexamapi.service.TextContentService;
import com.exam.onlineexamapi.utils.ExamUtil;
import com.exam.onlineexamapi.utils.JsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExamPaperQuestionCustomerAnswerServiceImpl implements ExamPaperQuestionCustomerAnswerService {

    @Resource
    ExamPaperQuestionCustomerAnswerMapper examPaperQuestionCustomerAnswerMapper;

    @Resource
    TextContentService textContentService;

    @Override
    public void insertList(List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers) {
        examPaperQuestionCustomerAnswerMapper.insertList(examPaperQuestionCustomerAnswers);
    }

    @Override
    public List<ExamPaperQuestionCustomerAnswer> selectListByPaperAnswerId(Integer id) {
        return examPaperQuestionCustomerAnswerMapper.selectListByPaperAnswerId(id);
    }

    /**
     * 试卷问题答题信息转成vm回传
     *
     * @param qa
     * @return
     */
    @Override
    public ExamPaperSubmitItemVM examPaperQuestionCustomerAnswerToVM(ExamPaperQuestionCustomerAnswer qa) {
        ExamPaperSubmitItemVM examPaperSubmitItemVM = new ExamPaperSubmitItemVM();
        examPaperSubmitItemVM.setId(qa.getId());
        examPaperSubmitItemVM.setQuestionId(qa.getQuestionId());
        examPaperSubmitItemVM.setDoRight(qa.getDoRight());
        examPaperSubmitItemVM.setItemOrder(qa.getItemOrder());
        examPaperSubmitItemVM.setQuestionScore(String.valueOf(qa.getQuestionScore()));
        examPaperSubmitItemVM.setScore(String.valueOf(qa.getCustomerScore()));
        setSpecialToVM(examPaperSubmitItemVM, qa);
        return examPaperSubmitItemVM;
    }

    @Override
    public Integer updateScore(List<ExamPaperAnswerUpdate> examPaperAnswerUpdates) {
        return examPaperQuestionCustomerAnswerMapper.updateScore(examPaperAnswerUpdates);
    }

    private void setSpecialToVM(ExamPaperSubmitItemVM examPaperSubmitItemVM, ExamPaperQuestionCustomerAnswer examPaperQuestionCustomerAnswer) {
        QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.fromCode(examPaperQuestionCustomerAnswer.getQuestionType());
        switch (questionTypeEnum) {
            case MultipleChoice:
                examPaperSubmitItemVM.setContent(examPaperQuestionCustomerAnswer.getAnswer());
                examPaperSubmitItemVM.setContentArray(ExamUtil.contentToArray(examPaperQuestionCustomerAnswer.getAnswer()));
                break;
            case GapFilling:
                TextContent textContent = textContentService.findById(examPaperQuestionCustomerAnswer.getTextContentId());
                List<String> correctAnswer = JsonUtil.toJsonListObject(textContent.getContent(), String.class);
                examPaperSubmitItemVM.setContentArray(correctAnswer);
                break;
            default:
                if (QuestionTypeEnum.needSaveTextContent(examPaperQuestionCustomerAnswer.getQuestionType())) {
                    TextContent content = textContentService.findById(examPaperQuestionCustomerAnswer.getTextContentId());
                    examPaperSubmitItemVM.setContent(content.getContent());
                } else {
                    examPaperSubmitItemVM.setContent(examPaperQuestionCustomerAnswer.getAnswer());
                }
                break;
        }
    }

    @Override
    public int save(ExamPaperQuestionCustomerAnswer record) {
        return 0;
    }

    @Override
    public int delete(ExamPaperQuestionCustomerAnswer record) {
        return 0;
    }

    @Override
    public int delete(List<ExamPaperQuestionCustomerAnswer> records) {
        return 0;
    }

    @Override
    public ExamPaperQuestionCustomerAnswer findById(Integer id) {
        return null;
    }

    @Override
    public PageResult findByPage(PageRequest pageRequest) {
        PageResult pageResult = null;
        Object userId = pageRequest.getParam("userId");
        Object subjectId = pageRequest.getParam("subjectId");
        if (subjectId != null) {
            pageResult = MybatisPageHelper.findByPage(pageRequest, examPaperQuestionCustomerAnswerMapper, "findPageBySubjectAndUser", subjectId, userId);
        } else {
            pageResult = MybatisPageHelper.findByPage(pageRequest, examPaperQuestionCustomerAnswerMapper, "findPageByUser", userId);
        }
        return pageResult;
    }
}
