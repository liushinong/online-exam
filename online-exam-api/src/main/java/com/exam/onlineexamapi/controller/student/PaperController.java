package com.exam.onlineexamapi.controller.student;

import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.PaperService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student/paper")
public class PaperController {
    @Resource
    PaperService paperService;

    @PostMapping("/findPageBySubject")
    public RestResult paperSelect(@RequestBody PageRequest pageRequest){
     return new RestResultBuilder<>().success(paperService.findPageBySubjectId(pageRequest));
    }

}
