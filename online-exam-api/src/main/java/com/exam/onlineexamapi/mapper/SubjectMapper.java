package com.exam.onlineexamapi.mapper;

import com.exam.onlineexamapi.domain.dto.admin.question.SubjectEditDTO;
import com.exam.onlineexamapi.domain.entity.Subject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubjectMapper {
    List<Subject> findByPage();
    List<Subject> findPageByTeacherAndName(Integer teacherId, String name);
    Integer insert(Subject subject);
    Integer update(SubjectEditDTO subjectEditDTO);
    Subject findById(Long id);
    Integer delete(Long id);
}
