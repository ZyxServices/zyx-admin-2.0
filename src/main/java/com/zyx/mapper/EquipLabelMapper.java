package com.zyx.mapper;

import com.zyx.model.EquipLabel;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zjx on 2016/11/14.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
@Repository("equipLabelMapper")
public interface EquipLabelMapper extends Mapper<EquipLabel>{

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

    /**
     * 设置标签启用或者禁用
     * @param equipLabel
     * @return
     */
    int updateState(EquipLabel equipLabel);

    /**
     * 根据状态查询标签集合
     * @param
     * @return
     */
    List<EquipLabel> queryByState(EquipLabel equipLabel);
}
