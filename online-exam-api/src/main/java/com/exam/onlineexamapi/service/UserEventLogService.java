package com.exam.onlineexamapi.service;

import com.exam.onlineexamapi.domain.entity.UserEventLog;
import com.exam.onlineexamapi.event.UserEvent;

public interface UserEventLogService {
    Integer insertByFilter(UserEventLog userEventLog);
}
