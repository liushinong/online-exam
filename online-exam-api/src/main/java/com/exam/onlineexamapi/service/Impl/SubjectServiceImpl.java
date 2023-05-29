package com.exam.onlineexamapi.service.Impl;

import com.exam.onlineexamapi.domain.dto.admin.question.SubjectEditDTO;
import com.exam.onlineexamapi.domain.dto.admin.subject.SubjectUserDTO;
import com.exam.onlineexamapi.domain.dto.student.subject.JoinSubjectDTO;
import com.exam.onlineexamapi.domain.entity.Subject;
import com.exam.onlineexamapi.domain.vo.student.SubjectVO;
import com.exam.onlineexamapi.mapper.SubjectMapper;
import com.exam.onlineexamapi.page.MybatisPageHelper;
import com.exam.onlineexamapi.page.PageRequest;
import com.exam.onlineexamapi.page.PageResult;
import com.exam.onlineexamapi.result.RestResult;
import com.exam.onlineexamapi.result.RestResultBuilder;
import com.exam.onlineexamapi.service.SubjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Resource
    SubjectMapper subjectMapper;

    @Override
    public int save(Subject record) {
        return 0;
    }

    @Override
    public int delete(Subject record) {
        return subjectMapper.delete(record.getId());
    }

    @Override
    public int delete(List<Subject> records) {
        return 0;
    }

    @Override
    public Subject findById(Integer id) {
        return subjectMapper.findById(id);
    }

    @Override
    public PageResult findByPage(PageRequest pageRequest) {
        return MybatisPageHelper.findByPage(pageRequest, subjectMapper);
    }


    @Override
    public PageResult findPageByTeacherAndName(PageRequest pageRequest) {
        Object teacherId = pageRequest.getParam("teacherId");
        Object name = pageRequest.getParam("name");
        return MybatisPageHelper.findByPage(pageRequest, subjectMapper, "findPageByTeacherAndName", teacherId, name);
    }

    @Override
    public Integer insertSubject(SubjectEditDTO subjectEditDTO) {
        Subject subject = new Subject();
        subject.setName(subjectEditDTO.getName());
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += random.nextInt(10);
        }
        subject.setSubjectCode(result);
        subject.setImage(subjectEditDTO.getImg());
        subject.setTeacherId(subjectEditDTO.getTeacherId());
        subject.setCreateTime(new Timestamp(new Date().getTime()));
        return subjectMapper.insert(subject);
    }

    @Override
    public Integer updateSubject(SubjectEditDTO subjectEditDTO) {
        return subjectMapper.update(subjectEditDTO);
    }

    @Override
    public List<SubjectVO> getSubjectByStuId(Integer id) {
        return subjectMapper.getSubjectByStuId(id);
    }

    @Override
    public Integer deleteSubjectUser(SubjectUserDTO subjectUserDTO) {
        return subjectMapper.deleteSubjectUser(subjectUserDTO.getSubjectId(), subjectUserDTO.getUserId());
    }

    @Override
    public RestResult<Object> joinSubject(JoinSubjectDTO joinSubjectDTO) {

        Integer subjectId = subjectMapper.selectByCode(joinSubjectDTO.getSubjectCode());
        if (subjectId != null) {
            Integer flag = subjectMapper.judgment(joinSubjectDTO.getUserId(), subjectId);
            if (flag == 0) {
                Date date = new Date();
                Integer join = subjectMapper.joinSubject(joinSubjectDTO.getUserId(), subjectId, new Timestamp(date.getTime()));
                return new RestResultBuilder<>().success(join);
            } else {
                return new RestResultBuilder<>().error("已加入该课程");
            }
        } else {
            return new RestResultBuilder<>().error("课程不存在");
        }
    }
}
