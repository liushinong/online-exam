package com.exam.onlineexamapi.event;

import com.exam.onlineexamapi.domain.entity.UserEventLog;
import org.springframework.context.ApplicationEvent;

public class UserEvent extends ApplicationEvent {

    private UserEventLog userEventLog;

    public UserEvent(UserEventLog userEventLog) {
        super(userEventLog);
        this.userEventLog = userEventLog;
    }

    public UserEventLog getUserEventLog() {
        return this.userEventLog;
    }
}
