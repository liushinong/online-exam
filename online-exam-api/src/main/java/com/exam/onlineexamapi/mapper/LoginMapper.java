package com.exam.onlineexamapi.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    Integer logon(String username, String password, String salt);
}
