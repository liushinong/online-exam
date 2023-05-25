package com.exam.onlineexamapi.controller.admin;

import com.exam.onlineexamapi.domain.dto.admin.question.QuestionEditRequestDTO;
import com.exam.onlineexamapi.domain.entity.Question;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource
    QuestionService questionService;

    @PostMapping("/page")
    public RestResult pageList(@RequestBody PageRequest pageRequest) {
        return new RestResultBuilder<>().success(questionService.findByPage(pageRequest));
    }

    @PostMapping("/select")
    public RestResult select(@RequestParam Integer id) {
        return new RestResultBuilder<>().success(questionService.getQuestionEditRequestVM(id));
    }

    @PostMapping("/edit")
    public RestResult insert(@RequestBody QuestionEditRequestDTO model) {
        if (null == model.getId()) {
            return new RestResultBuilder<>().success(questionService.insertQuestion(model));
        } else {
            return new RestResultBuilder<>().success(questionService.updateQuestion(model));
        }
    }

    @PostMapping("/delete")
    public RestResult del(@RequestParam Integer id) {
//        Question question = questionService.findById(id);
        return new RestResultBuilder<>().success(questionService.delQuestion(id));
    }
}
