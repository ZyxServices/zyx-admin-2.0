package com.zyx.mapper;

import com.zyx.model.CourseLabel;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by zjx on 2016/11/10.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
@Repository("courseLabelMapper")
public interface CourseLabelMapper extends Mapper<CourseLabel>{

    /**
     * 删除标签
     * @param labelId 标签id
     * @return
     */
//    int deleteByPrimaryKey(Integer labelId);

    /**
     * 查询所有标签
     * @return
     */
//    List<CourseLabel> queryCourseLabel();

    /**
     * 以上两种方法通过继承mapper自动实现
     */
}
