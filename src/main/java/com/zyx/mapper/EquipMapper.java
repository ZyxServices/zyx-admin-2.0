package com.zyx.mapper;

import com.zyx.model.Equip;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zjx on 2016/11/11.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
public interface EquipMapper extends Mapper<Equip> {

    /**
     * 动态分页查询装备贴
     * @param equip 装备贴
     * @return
     */
    List<Equip> queryEquip(Equip equip);

    /**
     * 根据帖子类型查询帖子
     * @param equipType
     * @return
     */
    List<Equip> queryByType(int equipType);



}
