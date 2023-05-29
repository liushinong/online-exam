package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.entity.User;
import com.exam.onlineexamapi.domain.vo.admin.DetailVo;
import com.exam.onlineexamapi.domain.vo.student.InformationVo;
import com.exam.onlineexamapi.domain.vo.student.SubjectVO;
import com.exam.onlineexamapi.mapper.UserMapper;
import com.exam.onlineexamapi.page.MybatisPageHelper;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public RestResult findByName(String username) {
        return new RestResultBuilder<>().success(userMapper.findByName(username));
    }

    @Override
    public RestResult<Object> findByDate(Integer createId) {
        return new RestResultBuilder<>().success(userMapper.findByDate(createId));
    }

    @Override
    public RestResult selectAll(Integer teacherId) {
        DetailVo detailVo = DetailVo.builder().questionNum(userMapper.selectQuestionNum(teacherId)).
                subjectNum(userMapper.selectSubjectNum(teacherId)).
                studentNum(userMapper.selectStudentNum(teacherId)).
                paperNum(userMapper.selectPaperNum(teacherId)).build();
        return new RestResultBuilder<>().success(detailVo);
    }

    @Override
    public RestResult selectInformation(Integer studentId) {
        InformationVo informationVo = InformationVo.builder().subjectNum(userMapper.subjectNum2(studentId)).
                paperNum(userMapper.paperNum2(studentId)).
                finishPaperNum(userMapper.finishPaperNum(studentId)).
                wrongQuestionNum(userMapper.wrongQuestionNum(studentId)).build();
        return new RestResultBuilder<>().success(informationVo);

    }

    @Override
    public PageResult findSubjectByPage(PageRequest pageRequest) {
        PageResult pageResult = null;
        Object studentId = pageRequest.getParam("studentId");
        Object search = pageRequest.getParam("search");

        if (search != null) {
            pageResult = MybatisPageHelper.findByPage(pageRequest, userMapper, "fuzzyQuery", studentId, search);
        } else {
            pageResult = MybatisPageHelper.findByPage(pageRequest, userMapper, "findSubjectByPage", studentId);
        }

        return pageResult;

    }

    @Override
    public int save(User record) {
        return 0;
    }

    @Override
    public int delete(User record) {
        return 0;
    }

    @Override
    public int delete(List<User> records) {
        return 0;
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public PageResult findByPage(PageRequest pageRequest) {
        PageResult pageResult = null;
        Object subjectId = pageRequest.getParam("subjectId");

        if (subjectId != null) {
            pageResult = MybatisPageHelper.findByPage(pageRequest, userMapper, "findBySubject", subjectId);
        } else {
            pageResult = MybatisPageHelper.findByPage(pageRequest, userMapper);
        }
        return pageResult;
    }
}
