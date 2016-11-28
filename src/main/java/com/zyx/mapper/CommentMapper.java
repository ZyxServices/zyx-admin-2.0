package com.zyx.mapper;

import com.zyx.base.BaseMapper;
import com.zyx.dto.CommentDto;
import com.zyx.model.Comment;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentDto> queryByTypeAndId(Integer id,Integer type);
}