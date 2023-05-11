package com.exam.onlineexamapi.mapper;

import com.exam.onlineexamapi.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByName(String username);
}
