package com.exam.onlineexamapi.mapper;

import com.exam.onlineexamapi.domain.DO.user.CreateDate;
import com.exam.onlineexamapi.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByName(String username);

    List<User> findByPage();

    List<User> findBySubject(Integer subjectId);

    List<CreateDate> findByDate(Integer createId);

    User findById(Integer userId);

    Integer selectQuestionNum(Integer teacherId);
    Integer selectSubjectNum(Integer teacherId);
    Integer selectStudentNum(Integer teacherId);
    Integer selectPaperNum(Integer teacherId);

    Integer subjectNum2(Integer studentId);
    Integer paperNum2(Integer studentId);
    Integer finishPaperNum(Integer studentId);
    Integer wrongQuestionNum(Integer studentId);
}
