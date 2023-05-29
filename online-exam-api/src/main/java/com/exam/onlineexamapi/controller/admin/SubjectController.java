package com.exam.onlineexamapi.controller.admin;

import com.exam.onlineexamapi.domain.dto.admin.question.SubjectEditDTO;
import com.exam.onlineexamapi.domain.dto.admin.subject.SubjectUserDTO;
import com.exam.onlineexamapi.domain.entity.Subject;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/subject")
public class SubjectController {

    @Resource
    SubjectService subjectService;

    @PostMapping("/page")
    public RestResult pageList(@RequestBody PageRequest pageRequest) {
        return new RestResultBuilder<>().success(subjectService.findPageByTeacherAndName(pageRequest));
    }

    @PostMapping("/edit")
    public RestResult insertOrEdit(@RequestBody SubjectEditDTO subjectEditDTO) {
        if (null == subjectEditDTO.getId()) {
            return new RestResultBuilder<>().success(subjectService.insertSubject(subjectEditDTO));
        } else {
            return new RestResultBuilder<>().success(subjectService.updateSubject(subjectEditDTO));
        }
    }

    @PostMapping("/delete")
    public RestResult delete(@RequestParam Integer id) {
        Subject subject = subjectService.findById(id);
        return new RestResultBuilder<>().success(subjectService.delete(subject));
    }

    @PostMapping("/deleteSubjectUser")
    public RestResult delectSubjectUser(@RequestBody SubjectUserDTO subjectUserDTO){
        return new RestResultBuilder<>().success(subjectService.deleteSubjectUser(subjectUserDTO));
    }

}
