package com.exam.onlineexamapi.service;

import com.exam.onlineexamapi.domain.entity.User;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;
import com.exam.onlineexamapi.result.RestResult;

public interface UserService extends CurdService<User>{
    RestResult findByName(String username);

    RestResult<Object> findByDate(Integer createId);

    RestResult selectAll(Integer teacherId);

    RestResult selectInformation(Integer studentId);


    PageResult findSubjectByPage(PageRequest pageRequest);
}
