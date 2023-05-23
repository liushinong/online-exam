package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.DO.paper.ExamPaperQuestionItemObject;
import com.exam.onlineexamapi.domain.DO.paper.ExamPaperTitleItemObject;
import com.exam.onlineexamapi.domain.dto.admin.exam.ExamPaperEditRequestDTO;
import com.exam.onlineexamapi.domain.dto.admin.exam.ExamPaperTitleItemDTO;
import com.exam.onlineexamapi.domain.dto.admin.question.QuestionEditRequestDTO;
import com.exam.onlineexamapi.domain.entity.ExamPaper;
import com.exam.onlineexamapi.domain.entity.Question;
import com.exam.onlineexamapi.domain.entity.TextContent;
import com.exam.onlineexamapi.domain.enums.ActionEnum;
import com.exam.onlineexamapi.domain.enums.ExamPaperTypeEnum;
import com.exam.onlineexamapi.mapper.ExamPaperMapper;
import com.exam.onlineexamapi.mapper.QuestionMapper;
import com.exam.onlineexamapi.page.MybatisPageHelper;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;
import com.exam.onlineexamapi.service.ExamPaperService;
import com.exam.onlineexamapi.service.QuestionService;
import com.exam.onlineexamapi.service.TextContentService;
import com.exam.onlineexamapi.utils.DateTimeUtil;
import com.exam.onlineexamapi.utils.ExamUtil;
import com.exam.onlineexamapi.utils.JsonUtil;
import com.exam.onlineexamapi.utils.ModelMapperSingle;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ExamPaperServiceImpl implements ExamPaperService {

    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();
    @Resource
    TextContentService textContentService;
    @Resource
    ExamPaperMapper examPaperMapper;
    @Resource
    QuestionMapper questionMapper;
    @Resource
    QuestionService questionService;

    /**
     * 插入、修改
     *
     * @param examPaperEditRequestDTO
     * @return
     */
    @Override
    public ExamPaper savePaperFromVM(ExamPaperEditRequestDTO examPaperEditRequestDTO) {
        ActionEnum actionEnum = (examPaperEditRequestDTO.getId() == null) ? ActionEnum.ADD : ActionEnum.UPDATE;
        Date date = new Date();
        List<ExamPaperTitleItemDTO> titleItems = examPaperEditRequestDTO.getTitleItems();
        List<ExamPaperTitleItemObject> frameTextContentList = frameTextContentFromVM(titleItems);
        String frameTextContentStr = JsonUtil.toJsonStr(frameTextContentList);

        ExamPaper examPaper;
        if (actionEnum == ActionEnum.ADD) {
            examPaper = modelMapper.map(examPaperEditRequestDTO, ExamPaper.class);
            TextContent textContent = new TextContent(frameTextContentStr, date);
            textContentService.save(textContent);
            examPaper.setFrameTextContentId(textContent.getId());
            examPaper.setCreateTime(new Timestamp(date.getTime()));
            examPaper.setCreateUser(examPaperEditRequestDTO.getUserId());
            examPaperFromVM(examPaperEditRequestDTO, examPaper, titleItems);
            examPaperMapper.insertSelective(examPaper);
        } else {
            examPaper = examPaperMapper.selectByPrimaryKey(examPaperEditRequestDTO.getId());
            TextContent textContent = textContentService.findById(examPaper.getFrameTextContentId());
            textContent.setContent(frameTextContentStr);
            textContentService.updateById(textContent);
            modelMapper.map(examPaperEditRequestDTO, examPaper);
            examPaperFromVM(examPaperEditRequestDTO, examPaper, titleItems);
            examPaperMapper.updateByPrimaryKeySelective(examPaper);
        }
        return examPaper;
    }

    @Override
    public Integer delExamPaper(Integer id) {
        return examPaperMapper.deleteById(id);
    }

    @Override
    public ExamPaperEditRequestDTO examPaperToVM(Integer id) {
        // 获取到试卷
        ExamPaper examPaper = examPaperMapper.selectByPrimaryKey(id);
        // 映射
        ExamPaperEditRequestDTO vm = modelMapper.map(examPaper, ExamPaperEditRequestDTO.class);
        // 获取框架
        TextContent textContent = textContentService.findById(examPaper.getFrameTextContentId());
        // 框架转成 list <name, questionItems>
        List<ExamPaperTitleItemObject> examPaperTitleItemObjects = JsonUtil.toJsonListObject(textContent.getContent(), ExamPaperTitleItemObject.class);
        // 获取到试卷里所有questionId
        List<Integer> questionIds = examPaperTitleItemObjects.stream().flatMap(t -> t.getQuestionItems().stream().map(q -> q.getId())).collect(Collectors.toList());
        // 根据id获取到所有question实体
        List<Question> questions = questionMapper.selectByIds(questionIds);
        // t 就是 list<name, questionItems> 中的每一个 也就是 titleItem
        List<ExamPaperTitleItemDTO> examPaperTitleItemDTOS = examPaperTitleItemObjects.stream().map(t -> {
            // 将 list<name, questionItems> 每一个 映射到 titleItemDTO
            ExamPaperTitleItemDTO titleItemDTO = modelMapper.map(t, ExamPaperTitleItemDTO.class);
            // i 就是 titleItem 中 的 每一个 questionItem
            List<QuestionEditRequestDTO> questionItemsDTO = t.getQuestionItems().stream().map(i -> {
                Question question = questions.stream().filter(q -> q.getId() == i.getId()).findFirst().get();
                // 通过 id 将 question 转换成 QuestionEditRequestDTO
                QuestionEditRequestDTO questionEditRequestDTO = questionService.getQuestionEditRequestVM(question);
                questionEditRequestDTO.setItemOrder(i.getItemOrder());
                return questionEditRequestDTO;
            }).collect(Collectors.toList());
            titleItemDTO.setQuestionItems(questionItemsDTO);
            return titleItemDTO;
        }).collect(Collectors.toList());
        vm.setTitleItems(examPaperTitleItemDTOS);
        vm.setScore(String.valueOf(examPaper.getScore()));
        if (ExamPaperTypeEnum.TimeLimit == ExamPaperTypeEnum.fromCode((int) examPaper.getPaperType())) {
            List<String> limitDateTime = Arrays.asList(DateTimeUtil.dateFormat(examPaper.getLimitStartTime()), DateTimeUtil.dateFormat(examPaper.getLimitEndTime()));
            vm.setLimitDateTime(limitDateTime);
        }
        return vm;
    }

    private void examPaperFromVM(ExamPaperEditRequestDTO examPaperEditRequestDTO, ExamPaper examPaper, List<ExamPaperTitleItemDTO> titleItems) {
        Integer questionCount = titleItems.stream().mapToInt(t -> t.getQuestionItems().size()).sum();
        Integer score = titleItems.stream().flatMapToInt(t -> t.getQuestionItems().stream().mapToInt(q -> ExamUtil.scoreFromVM(q.getScore()))).sum();
        examPaper.setQuestionCount(questionCount);
        examPaper.setScore(score);
        List<String> dateTimes = examPaperEditRequestDTO.getLimitDateTime();
        if (ExamPaperTypeEnum.TimeLimit == ExamPaperTypeEnum.fromCode((int) examPaper.getPaperType())) {
            examPaper.setLimitStartTime(new Timestamp((DateTimeUtil.parse(dateTimes.get(0), DateTimeUtil.STANDER_FORMAT)).getTime()));
            examPaper.setLimitEndTime(new Timestamp((DateTimeUtil.parse(dateTimes.get(1), DateTimeUtil.STANDER_FORMAT)).getTime()));
        }
    }

    public List<ExamPaperTitleItemObject> frameTextContentFromVM(List<ExamPaperTitleItemDTO> titleItems) {
        AtomicInteger index = new AtomicInteger(1);
        return titleItems.stream().map(t -> {
            ExamPaperTitleItemObject titleItem = modelMapper.map(t, ExamPaperTitleItemObject.class);
            List<ExamPaperQuestionItemObject> questionItems = t.getQuestionItems().stream().map(q -> {
                ExamPaperQuestionItemObject examPaperQuestionItemObject = modelMapper.map(q, ExamPaperQuestionItemObject.class);
                examPaperQuestionItemObject.setItemOrder(index.getAndIncrement());
                return examPaperQuestionItemObject;
            }).collect(Collectors.toList());
            titleItem.setQuestionItems(questionItems);
            return titleItem;
        }).collect(Collectors.toList());
    }

    @Override
    public int save(ExamPaper record) {
        return 0;
    }

    @Override
    public int delete(ExamPaper record) {
        return 0;
    }

    @Override
    public int delete(List<ExamPaper> records) {
        return 0;
    }

    @Override
    public ExamPaper findById(Long id) {
        return null;
    }

    @Override
    public PageResult findByPage(PageRequest pageRequest) {
        PageResult pageResult = null;
        Object subjectId = pageRequest.getParam("subjectId");
        Object teacherId = pageRequest.getParam("teacherId");
        if (subjectId != null) {
            pageResult = MybatisPageHelper.findByPage(pageRequest, examPaperMapper, "findPageBySubjectAndTeacher", subjectId, teacherId);
        } else {
            pageResult = MybatisPageHelper.findByPage(pageRequest, examPaperMapper, "findPageByTeacher", teacherId);
        }
        return pageResult;
    }
}
