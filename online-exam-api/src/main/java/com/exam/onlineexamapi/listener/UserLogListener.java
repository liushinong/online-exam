package com.exam.onlineexamapi.listener;

import com.exam.onlineexamapi.event.UserEvent;
import com.exam.onlineexamapi.service.UserEventLogService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserLogListener implements ApplicationListener<UserEvent> {

    @Resource
    UserEventLogService userEventLogService;

    @Override
    public void onApplicationEvent(UserEvent userEvent) {
        userEventLogService.insertByFilter(userEvent.getUserEventLog());
    }
}
