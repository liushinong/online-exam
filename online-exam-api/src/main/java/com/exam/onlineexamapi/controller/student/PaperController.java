package com.exam.onlineexamapi.controller.student;

import com.exam.onlineexamapi.domain.DO.paper.PaperSelectDO;
import com.exam.onlineexamapi.domain.dto.student.paper.PaperSelectDTO;
import com.exam.onlineexamapi.domain.entity.Paper;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.PaperService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/paper")
public class PaperController {
    @Resource
    PaperService paperService;

    @PostMapping("/paperSelect")
    public RestResult  paperSelect(@RequestBody PaperSelectDTO paperSelectDTO){
     return paperService.paperSelect(PaperSelectDTO.getSubjectId());
    }

}
