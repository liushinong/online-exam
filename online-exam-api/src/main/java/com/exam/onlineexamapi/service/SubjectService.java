package com.exam.onlineexamapi.service;

import com.exam.onlineexamapi.domain.dto.admin.question.SubjectEditDTO;
import com.exam.onlineexamapi.domain.dto.admin.subject.SubjectUserDTO;
import com.exam.onlineexamapi.domain.dto.student.subject.JoinSubjectDTO;
import com.exam.onlineexamapi.domain.entity.Subject;
import com.exam.onlineexamapi.domain.vo.student.SubjectVO;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;
import com.exam.onlineexamapi.result.RestResult;

import java.util.List;

public interface SubjectService extends CurdService<Subject>{
    PageResult findPageByTeacherAndName(PageRequest pageRequest);

    Integer insertSubject(SubjectEditDTO subjectEditDTO);

    Integer updateSubject(SubjectEditDTO subjectEditDTO);

    List<SubjectVO> getSubjectByStuId(Integer id);

    Integer deleteSubjectUser(SubjectUserDTO subjectUserDTO);

    RestResult<Object> joinSubject(JoinSubjectDTO joinSubjectDTO);
}
