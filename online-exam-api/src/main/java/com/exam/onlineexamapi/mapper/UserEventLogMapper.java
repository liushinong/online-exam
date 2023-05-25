package com.exam.onlineexamapi.mapper;

import com.exam.onlineexamapi.domain.entity.UserEventLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserEventLogMapper {
    Integer insertSelective(UserEventLog userEventLog);
}
