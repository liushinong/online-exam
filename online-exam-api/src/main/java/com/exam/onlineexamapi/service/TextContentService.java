package com.exam.onlineexamapi.service;

import com.exam.onlineexamapi.domain.entity.TextContent;

public interface TextContentService extends CurdService<TextContent>{
    Integer updateById(TextContent textContent);
}
