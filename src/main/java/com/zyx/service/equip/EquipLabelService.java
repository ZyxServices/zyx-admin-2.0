package com.zyx.service.equip;

import com.zyx.model.EquipLabel;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * Created by zjx on 2016/11/14.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *          装备标签的业务层接口
 */
public interface EquipLabelService extends BaseService<EquipLabel>{
    /**
     * 添加标签
     * @param equipLabel
     * @return
     */
    Map<String,Object> insertEquipLabel(EquipLabel equipLabel);

    /**
     * 删除标签
     * @param equipLabelId 标签id
     * @return
     */
    Map<String,Object> delEquipLabel(Integer equipLabelId);

    /**
     * 查询所有标签
     * @return
     */
    Map<String,Object> queryEquipLabel();

    /**
     * 设置标签启用或者禁用
     * @param equipLabel
     * @return
     */
    Map<String,Object> updateEquipLabel(EquipLabel equipLabel);

    /**
     * 根据状态查询标签集合
     * @param
     * @return
     */
    Map<String,Object>queryByState();
}
