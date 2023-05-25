package com.exam.onlineexamapi.mapper;

import com.exam.onlineexamapi.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByName(String username);

    List<User> findByPage();

    List<User> findBySubject(Integer subjectId);

    User findById(Integer userId);
}
