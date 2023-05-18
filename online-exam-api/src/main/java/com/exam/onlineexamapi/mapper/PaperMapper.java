package com.exam.onlineexamapi.mapper;
import com.exam.onlineexamapi.domain.DO.paper.PaperSelectDO;
import com.exam.onlineexamapi.domain.entity.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaperMapper {

     List<PaperSelectDO> paperSelect(Long subjectId);


}