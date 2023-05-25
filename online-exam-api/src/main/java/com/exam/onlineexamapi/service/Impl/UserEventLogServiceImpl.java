package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.entity.UserEventLog;
import com.exam.onlineexamapi.mapper.UserEventLogMapper;
import com.exam.onlineexamapi.service.UserEventLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserEventLogServiceImpl implements UserEventLogService {

    @Resource
    UserEventLogMapper userEventLogMapper;

    @Override
    public Integer insertByFilter(UserEventLog userEventLog) {
        return userEventLogMapper.insertSelective(userEventLog);
    }
}
