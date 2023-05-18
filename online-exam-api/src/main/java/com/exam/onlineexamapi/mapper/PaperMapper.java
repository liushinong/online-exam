package com.exam.onlineexamapi.mapper;
import com.exam.onlineexamapi.domain.entity.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaperMapper {

     List<Paper> findPageBySubjectId(Integer subjectId);
}