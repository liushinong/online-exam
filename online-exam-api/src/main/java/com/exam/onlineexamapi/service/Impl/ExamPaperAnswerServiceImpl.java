package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.DO.ExamPaperAnswerInfo;
import com.exam.onlineexamapi.domain.DO.paper.ExamPaperTitleItemObject;
import com.exam.onlineexamapi.domain.dto.student.exam.ExamPaperSubmitItemVM;
import com.exam.onlineexamapi.domain.dto.student.exam.ExamPaperSubmitVM;
import com.exam.onlineexamapi.domain.entity.ExamPaper;
import com.exam.onlineexamapi.domain.entity.ExamPaperAnswer;
import com.exam.onlineexamapi.domain.entity.ExamPaperQuestionCustomerAnswer;
import com.exam.onlineexamapi.domain.entity.Question;
import com.exam.onlineexamapi.domain.enums.ExamPaperAnswerStatusEnum;
import com.exam.onlineexamapi.domain.enums.ExamPaperTypeEnum;
import com.exam.onlineexamapi.domain.enums.QuestionTypeEnum;
import com.exam.onlineexamapi.domain.other.ExamPaperAnswerUpdate;
import com.exam.onlineexamapi.mapper.ExamPaperAnswerMapper;
import com.exam.onlineexamapi.mapper.ExamPaperMapper;
import com.exam.onlineexamapi.mapper.QuestionMapper;
import com.exam.onlineexamapi.page.MybatisPageHelper;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;
import com.exam.onlineexamapi.service.ExamPaperAnswerService;
import com.exam.onlineexamapi.service.ExamPaperQuestionCustomerAnswerService;
import com.exam.onlineexamapi.service.TextContentService;
import com.exam.onlineexamapi.utils.ExamUtil;
import com.exam.onlineexamapi.utils.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamPaperAnswerServiceImpl implements ExamPaperAnswerService {

    @Resource
    ExamPaperMapper examPaperMapper;
    @Resource
    ExamPaperAnswerMapper examPaperAnswerMapper;
    @Resource
    TextContentService textContentService;
    @Resource
    QuestionMapper questionMapper;
    @Resource
    ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService;

    /**
     * 计算试卷提交结果（不入库）
     * @param examPaperSubmitVM
     * @param userId
     * @return
     */
    @Override
    public ExamPaperAnswerInfo calculateExamPaperAnswer(ExamPaperSubmitVM examPaperSubmitVM, Integer userId) {
        ExamPaperAnswerInfo examPaperAnswerInfo = new ExamPaperAnswerInfo();
        Date now = new Date();
        // 获取试卷
        ExamPaper examPaper = examPaperMapper.selectByPrimaryKey(examPaperSubmitVM.getId());
        ExamPaperTypeEnum paperTypeEnum = ExamPaperTypeEnum.fromCode(examPaper.getPaperType());
        // 任务试卷只能做一次
        if (paperTypeEnum == ExamPaperTypeEnum.Fixed || paperTypeEnum == ExamPaperTypeEnum.TimeLimit || paperTypeEnum == ExamPaperTypeEnum.Task) {
            ExamPaperAnswer examPaperAnswer = examPaperAnswerMapper.getByPidUid(examPaperSubmitVM.getId(), userId);
            if (null != examPaperAnswer) {
                return null;
            }
        }
        // 获取试卷框架
        String frameTextContent = textContentService.findById(examPaper.getFrameTextContentId()).getContent();
        // 解析试卷框架为list<name, questionItems>
        List<ExamPaperTitleItemObject> examPaperTitleItemObjects = JsonUtil.toJsonListObject(frameTextContent, ExamPaperTitleItemObject.class);
        // 获取所有题目id
        List<Integer> questionIds = examPaperTitleItemObjects.stream().flatMap(t -> t.getQuestionItems().stream().map(q -> q.getId())).collect(Collectors.toList());
        // 根据id获取题目
        List<Question> questions = questionMapper.selectByIds(questionIds);
        // 将题目结构转换为题目答案
        List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers = examPaperTitleItemObjects.stream()
                .flatMap(
                        t -> t.getQuestionItems().stream().map(q -> {
                            // q 是 试卷框架里的每一个问题
                            Question question = questions.stream().filter(tq -> tq.getId() == q.getId()).findFirst().get();
                            ExamPaperSubmitItemVM customerQuestionAnswer = examPaperSubmitVM.getAnswerItems().stream()
                                    .filter(tq -> tq.getQuestionId() == q.getId()).findFirst().get();
                            return examPaperQuestionCustomerAnswerFromVM(question, customerQuestionAnswer, examPaper, q.getItemOrder(), userId, now);
                        })
                ).collect(Collectors.toList());
        ExamPaperAnswer examPaperAnswer = examPaperAnswerFromVM(examPaperSubmitVM, examPaper, examPaperQuestionCustomerAnswers, userId, now);
        examPaperAnswerInfo.setExamPaper(examPaper);
        examPaperAnswerInfo.setExamPaperAnswer(examPaperAnswer);
        examPaperAnswerInfo.setExamPaperQuestionCustomerAnswers(examPaperQuestionCustomerAnswers);
        return examPaperAnswerInfo;
    }

    @Override
    public Integer insertByFilter(ExamPaperAnswer examPaperAnswer) {
        return examPaperAnswerMapper.insertSelective(examPaperAnswer);
    }

    /**
     * 试卷答题信息转成vm传给前端
     * @param id
     * @return
     */
    @Override
    public ExamPaperSubmitVM examPaperAnswerToVM(Integer id) {
        ExamPaperSubmitVM examPaperSubmitVM = new ExamPaperSubmitVM();
        ExamPaperAnswer examPaperAnswer = examPaperAnswerMapper.selectById(id);
        examPaperSubmitVM.setId(examPaperAnswer.getId());
        examPaperSubmitVM.setDoTime(examPaperAnswer.getDoTime());
        examPaperSubmitVM.setScore(String.valueOf(examPaperAnswer.getUserScore()));
        List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers = examPaperQuestionCustomerAnswerService.selectListByPaperAnswerId(examPaperAnswer.getId());
        List<ExamPaperSubmitItemVM> examPaperSubmitItemVMS = examPaperQuestionCustomerAnswers.stream()
                .map(a -> examPaperQuestionCustomerAnswerService.examPaperQuestionCustomerAnswerToVM(a))
                .collect(Collectors.toList());
        examPaperSubmitVM.setAnswerItems(examPaperSubmitItemVMS);
        return examPaperSubmitVM;
    }

    /**
     * 试卷批改
     * @param examPaperSubmitVM
     * @return
     */
    @Override
    @Transactional
    public String judge(ExamPaperSubmitVM examPaperSubmitVM) {
        // 获取到试卷答案
        ExamPaperAnswer examPaperAnswer = examPaperAnswerMapper.selectById(examPaperSubmitVM.getId());
        // 获取传过来的参数中的 未批改的题目
        List<ExamPaperSubmitItemVM> judgeItems = examPaperSubmitVM.getAnswerItems().stream().filter(d -> d.getDoRight() == null).collect(Collectors.toList());
        List<ExamPaperAnswerUpdate> examPaperAnswerUpdates = new ArrayList<>(judgeItems.size());
        Integer customerScore = examPaperAnswer.getUserScore();
        Integer questionCorrect = examPaperAnswer.getQuestionCorrect();
        for (ExamPaperSubmitItemVM d : judgeItems) {
            ExamPaperAnswerUpdate examPaperAnswerUpdate = new ExamPaperAnswerUpdate();
            examPaperAnswerUpdate.setId(d.getId());
            examPaperAnswerUpdate.setCustomerScore(ExamUtil.scoreFromVM(d.getScore()));
            boolean doRight = examPaperAnswerUpdate.getCustomerScore().equals(ExamUtil.scoreFromVM(d.getQuestionScore()));
            examPaperAnswerUpdate.setDoRight(doRight);
            examPaperAnswerUpdates.add(examPaperAnswerUpdate);
            customerScore += examPaperAnswerUpdate.getCustomerScore();
            if (examPaperAnswerUpdate.getDoRight()) {
                questionCorrect++;
            }
        }
        examPaperAnswer.setUserScore(customerScore);
        examPaperAnswer.setQuestionCorrect(questionCorrect);
        examPaperAnswer.setStatus(ExamPaperAnswerStatusEnum.Complete.getCode());
        examPaperAnswerMapper.updateByPrimaryKeySelective(examPaperAnswer);
        examPaperQuestionCustomerAnswerService.updateScore(examPaperAnswerUpdates);
        return String.valueOf(customerScore);
    }

    /**
     * 试卷答案表 转存对象
     * @param examPaperSubmitVM
     * @param examPaper
     * @param examPaperQuestionCustomerAnswers
     * @param userId
     * @param now
     * @return
     */
    private ExamPaperAnswer examPaperAnswerFromVM(ExamPaperSubmitVM examPaperSubmitVM, ExamPaper examPaper, List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers, Integer userId, Date now) {
        ExamPaperAnswer examPaperAnswer = new ExamPaperAnswer();
        Integer systemScore = examPaperQuestionCustomerAnswers.stream().mapToInt(a -> a.getCustomerScore()).sum();
        long questionCorrect = examPaperQuestionCustomerAnswers.stream().filter(a -> a.getCustomerScore().equals(a.getQuestionScore())).count();
        examPaperAnswer.setPaperName(examPaper.getName());
        examPaperAnswer.setDoTime(examPaperSubmitVM.getDoTime());
        examPaperAnswer.setExamPaperId(examPaper.getId());
        examPaperAnswer.setCreateUser(userId);
        examPaperAnswer.setCreateTime(now);
        examPaperAnswer.setSubjectId( examPaper.getSubjectId());
        examPaperAnswer.setQuestionCount( examPaper.getQuestionCount());
        examPaperAnswer.setPaperScore( examPaper.getScore());
        examPaperAnswer.setPaperType( examPaper.getPaperType());
        examPaperAnswer.setSystemScore(systemScore);
        examPaperAnswer.setUserScore(systemScore);
        examPaperAnswer.setTaskExamId( examPaper.getTaskExamId());
        examPaperAnswer.setQuestionCorrect((int) questionCorrect);
        boolean needJudge = examPaperQuestionCustomerAnswers.stream().anyMatch(d -> QuestionTypeEnum.needSaveTextContent(d.getQuestionType()));
        if (needJudge) {
            examPaperAnswer.setStatus(ExamPaperAnswerStatusEnum.WaitJudge.getCode());
        } else {
            examPaperAnswer.setStatus(ExamPaperAnswerStatusEnum.Complete.getCode());
        }
        return examPaperAnswer;
    }

    /**
     * 用户提交的答案转换为存储对象
     * @param question
     * @param customerQuestionAnswer
     * @param examPaper
     * @param itemOrder
     * @param userId
     * @param now
     * @return
     */
    private ExamPaperQuestionCustomerAnswer examPaperQuestionCustomerAnswerFromVM(Question question, ExamPaperSubmitItemVM customerQuestionAnswer, ExamPaper examPaper, Integer itemOrder, Integer userId, Date now) {
        ExamPaperQuestionCustomerAnswer examPaperQuestionCustomerAnswer = new ExamPaperQuestionCustomerAnswer();
        examPaperQuestionCustomerAnswer.setQuestionId((int) question.getId());
        examPaperQuestionCustomerAnswer.setExamPaperId((int) examPaper.getId());
        examPaperQuestionCustomerAnswer.setQuestionScore(question.getScore());
        examPaperQuestionCustomerAnswer.setSubjectId((int) examPaper.getSubjectId());
        examPaperQuestionCustomerAnswer.setItemOrder(itemOrder);
        examPaperQuestionCustomerAnswer.setCreateTime(now);
        examPaperQuestionCustomerAnswer.setCreateUser(userId);
        examPaperQuestionCustomerAnswer.setQuestionType(question.getQuestionType());
        examPaperQuestionCustomerAnswer.setQuestionTextContentId((int) question.getInfoTextContentId());
        if (null == customerQuestionAnswer) {
            examPaperQuestionCustomerAnswer.setCustomerScore(0);
        } else {
            setSpecialFromVM(examPaperQuestionCustomerAnswer, question, customerQuestionAnswer);
        }
        return examPaperQuestionCustomerAnswer;
    }

    /**
     * 判断用户答案是否正确，保留用户提交的答案
     * @param examPaperQuestionCustomerAnswer
     * @param question
     * @param customerQuestionAnswer
     */
    private void setSpecialFromVM(ExamPaperQuestionCustomerAnswer examPaperQuestionCustomerAnswer, Question question, ExamPaperSubmitItemVM customerQuestionAnswer) {
        QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.fromCode(examPaperQuestionCustomerAnswer.getQuestionType());
        switch (questionTypeEnum) {
            case SingleChoice:
            case TrueFalse:
                examPaperQuestionCustomerAnswer.setAnswer(customerQuestionAnswer.getContent());
                examPaperQuestionCustomerAnswer.setDoRight(question.getCorrect().equals(customerQuestionAnswer.getContent()));
                examPaperQuestionCustomerAnswer.setCustomerScore(examPaperQuestionCustomerAnswer.getDoRight() ? question.getScore() : 0);
                break;
            case MultipleChoice:
                String customerAnswer = ExamUtil.contentToString(customerQuestionAnswer.getContentArray());
                examPaperQuestionCustomerAnswer.setAnswer(customerAnswer);
                examPaperQuestionCustomerAnswer.setDoRight(customerAnswer.equals(question.getCorrect()));
                examPaperQuestionCustomerAnswer.setCustomerScore(examPaperQuestionCustomerAnswer.getDoRight() ? question.getScore() : 0);
                break;
            case GapFilling:
                String correctAnswer = JsonUtil.toJsonStr(customerQuestionAnswer.getContentArray());
                examPaperQuestionCustomerAnswer.setAnswer(correctAnswer);
                examPaperQuestionCustomerAnswer.setCustomerScore(0);
                break;
            default:
                examPaperQuestionCustomerAnswer.setAnswer(customerQuestionAnswer.getContent());
                examPaperQuestionCustomerAnswer.setCustomerScore(0);
                break;
        }
    }

    @Override
    public int save(ExamPaperAnswer record) {
        return 0;
    }

    @Override
    public int delete(ExamPaperAnswer record) {
        return 0;
    }

    @Override
    public int delete(List<ExamPaperAnswer> records) {
        return 0;
    }

    @Override
    public ExamPaperAnswer findById(Integer id) {
        return examPaperAnswerMapper.selectById(id);
    }

    @Override
    public PageResult findByPage(PageRequest pageRequest) {
        PageResult pageResult = null;
        Object teacherId = pageRequest.getParam("teacherId");
        Object subjectId = pageRequest.getParam("subjectId");
        if (subjectId != null) {
            pageResult = MybatisPageHelper.findByPage(pageRequest, examPaperAnswerMapper, "findPageBySubjectAndTeacher", subjectId, teacherId);
        } else {
            pageResult = MybatisPageHelper.findByPage(pageRequest, examPaperAnswerMapper, "findPageByTeacher", teacherId);
        }
        return pageResult;
    }

    @Override
    public PageResult findPageByComplete(PageRequest pageRequest) {
        PageResult pageResult = null;
        Object teacherId = pageRequest.getParam("teacherId");
        Object subjectId = pageRequest.getParam("subjectId");
        if (subjectId != null) {
            pageResult = MybatisPageHelper.findByPage(pageRequest, examPaperAnswerMapper, "findPageBySubjectAndTeacherAndComplete", subjectId, teacherId);
        } else {
            pageResult = MybatisPageHelper.findByPage(pageRequest, examPaperAnswerMapper, "findPageByTeacherAndComplete", teacherId);
        }
        return pageResult;
    }

    @Override
    public PageResult findPageByUserId(PageRequest pageRequest) {
        PageResult pageResult = null;
        Object userId = pageRequest.getParam("userId");
        Object subjectId = pageRequest.getParam("subjectId");
        if (subjectId != null) {
            pageResult = MybatisPageHelper.findByPage(pageRequest, examPaperAnswerMapper, "findPageBySubjectAndUser", subjectId, userId);
        } else {
            pageResult = MybatisPageHelper.findByPage(pageRequest, examPaperAnswerMapper, "findPageByUser", userId);
        }
        List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers = (List<ExamPaperQuestionCustomerAnswer>) pageResult.getContent();
        return pageResult;
    }
}
